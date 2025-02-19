package handmadeguns.client.modelLoader.tcn_modelloaderMod;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import handmadeguns.client.render.IModelCustom_HMG;
import handmadeguns.client.modelLoader.obj_modelloaderMod.obj.HMGGroupObject;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelFormatException;
import org.lwjgl.opengl.GL11;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import static handmadeguns.HandmadeGunsCore.HMG_proxy;
import static org.lwjgl.opengl.GL11.GL_CULL_FACE;

/**
 * Techne model importer, based on iChun's Hats importer
 */
@SideOnly(Side.CLIENT)
public class TechneModel extends ModelBase implements IModelCustom_HMG {
    public static final List<String> cubeTypes = Arrays.asList(
            "d9e621f7-957f-4b77-b1ae-20dcd0da7751",
            "de81aa14-bd60-4228-8d8d-5238bcd3caaa"
            );
    
    private String fileName;
    private Map<String, byte[]> zipContents = new HashMap<String, byte[]>();
    
    private Map<String, List<ModelRenderer>> parts = new LinkedHashMap<String, List<ModelRenderer>>();
    private String texture = null;
    private Dimension textureDims = null;
    private int textureName;
    private boolean textureNameSet = false;
    public boolean endLoad = false;
    ExecutorService es;

    public TechneModel(ResourceLocation resource) throws ModelFormatException
    {
        HMG_proxy.AddModel(this);
        this.fileName = resource.toString();
        es = Executors.newCachedThreadPool();
        es.execute(() -> {
            try
            {
                IResource res = Minecraft.getMinecraft().getResourceManager().getResource(resource);
                loadTechneModel(res.getInputStream());
            }
            catch (Throwable e)
            {
                es.shutdown();
                throw new ModelFormatException("IO Exception reading model format", e);
            }
            endLoad = true;
            es.shutdown();
        });
    }

    @Override
    public ExecutorService getLoadThread() {
        return es;
    }
    
    private void loadTechneModel(InputStream stream) throws ModelFormatException
    {
        try
        {
            ZipInputStream zipInput = new ZipInputStream(stream);
            
            ZipEntry entry;
            while ((entry = zipInput.getNextEntry()) != null)
            {
                byte[] data = new byte[(int) entry.getSize()];
                // For some reason, using read(byte[]) makes reading stall upon reaching a 0x1E byte
                int i = 0;
                while (zipInput.available() > 0 && i < data.length)
                {
                    data[i++] = (byte)zipInput.read();
                }
                zipContents.put(entry.getName(), data);
            }
            
            byte[] modelXml = zipContents.get("model.xml");
            if (modelXml == null)
            {
                throw new ModelFormatException("Model " + fileName + " contains no model.xml file");
            }
            
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new ByteArrayInputStream(modelXml));
            
            NodeList nodeListTechne = document.getElementsByTagName("Techne");
            if (nodeListTechne.getLength() < 1)
            {
                throw new ModelFormatException("Model " + fileName + " contains no Techne tag");
            }
            
            NodeList nodeListModel = document.getElementsByTagName("Model");
            if (nodeListModel.getLength() < 1)
            {
                throw new ModelFormatException("Model " + fileName + " contains no Model tag");
            }
            
            NamedNodeMap modelAttributes = nodeListModel.item(0).getAttributes();
            if (modelAttributes == null)
            {
                throw new ModelFormatException("Model " + fileName + " contains a Model tag with no attributes");
            }
            
            Node modelTexture = modelAttributes.getNamedItem("texture");
            if (modelTexture != null)
            {
                texture = modelTexture.getTextContent();
            }

            NodeList textureDim = document.getElementsByTagName("TextureSize");
            if (textureDim.getLength() > 0)
            {
                try
                {
                    String[] tmp = textureDim.item(0).getTextContent().split(",");
                    if (tmp.length == 2)
                    {
                        this.textureDims = new Dimension(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
                    }
                }
                catch (NumberFormatException e)
                {
                    throw new ModelFormatException("Model " + fileName + " contains a TextureSize tag with invalid data");
                }
            }
    
            NodeList folders = document.getElementsByTagName("Folder");
            for(int folderID = 0;folderID < folders.getLength();folderID++){
                Node folder = folders.item(folderID);
                readFolderContents(folder);
            }
        }
        catch (ZipException e)
        {
            throw new ModelFormatException("Model " + fileName + " is not a valid zip file");
        }
        catch (IOException e)
        {
            throw new ModelFormatException("Model " + fileName + " could not be read", e);
        }
        catch (ParserConfigurationException e)
        {
            // hush
        }
        catch (SAXException e)
        {
            throw new ModelFormatException("Model " + fileName + " contains invalid XML", e);
        }
    }
    private void readFolderContents(Node folder){
        
        NodeList shapesAndChildFolder = folder.getChildNodes();
        ArrayList<ModelRenderer> shapesList = new ArrayList<ModelRenderer>();
        for (int i = 0; i < shapesAndChildFolder.getLength(); i++) {
            Node aNode = shapesAndChildFolder.item(i);
            if(aNode.getNodeName().equals("Folder"))readFolderContents(aNode);
            else
            if (aNode.getNodeName().equals("Shape")) {
                NamedNodeMap shapeAttributes = aNode.getAttributes();
                if (shapeAttributes == null) {
                    throw new ModelFormatException("Shape #" + (i + 1) + " in " + fileName + " has no attributes");
                }
            
                Node name = shapeAttributes.getNamedItem("name");
                String shapeName = null;
                if (name != null) {
                    shapeName = name.getNodeValue();
                }
                if (shapeName == null) {
                    shapeName = "Shape #" + (i + 1);
                }
            
                String shapeType = null;
                Node type = shapeAttributes.getNamedItem("type");
                if (type != null) {
                    shapeType = type.getNodeValue();
                }
                if (shapeType != null && !cubeTypes.contains(shapeType)) {
                    FMLLog.warning("Model shape [" + shapeName + "] in " + fileName + " is not a cube, ignoring");
                    continue;
                }
            
                try {
                    boolean mirrored = false;
                    String[] offset = new String[3];
                    String[] position = new String[3];
                    String[] rotation = new String[3];
                    String[] size = new String[3];
                    String[] textureOffset = new String[2];
                
                    NodeList shapeChildren = aNode.getChildNodes();
                    for (int j = 0; j < shapeChildren.getLength(); j++) {
                        Node shapeChild = shapeChildren.item(j);
                    
                        String shapeChildName = shapeChild.getNodeName();
                        String shapeChildValue = shapeChild.getTextContent();
                        if (shapeChildValue != null) {
                            shapeChildValue = shapeChildValue.trim();
                        
                            if (shapeChildName.equals("IsMirrored")) {
                                mirrored = !shapeChildValue.equals("False");
                            } else if (shapeChildName.equals("Offset")) {
                                offset = shapeChildValue.split(",");
                            } else if (shapeChildName.equals("Position")) {
                                position = shapeChildValue.split(",");
                            } else if (shapeChildName.equals("Rotation")) {
                                rotation = shapeChildValue.split(",");
                            } else if (shapeChildName.equals("Size")) {
                                size = shapeChildValue.split(",");
                            } else if (shapeChildName.equals("TextureOffset")) {
                                textureOffset = shapeChildValue.split(",");
                            }
                        }
                    }
                
                    // That's what the ModelBase subclassing is needed for
                    ModelRenderer cube = new ModelRenderer(this, Integer.parseInt(textureOffset[0]), Integer.parseInt(textureOffset[1]));
                    cube.mirror = mirrored;
                    if (this.textureDims != null) {
                        cube.setTextureSize((int) textureDims.getWidth(), (int) textureDims.getHeight());
                    }
                    cube.addBox(Float.parseFloat(offset[0]), Float.parseFloat(offset[1]), Float.parseFloat(offset[2]),
                            Integer.parseInt(size[0]), Integer.parseInt(size[1]), Integer.parseInt(size[2]));
                    cube.setRotationPoint(Float.parseFloat(position[0]), Float.parseFloat(position[1]) - 23.4F, Float.parseFloat(position[2]));
                
                    cube.rotateAngleX = (float) Math.toRadians(Float.parseFloat(rotation[0]));
                    cube.rotateAngleY = (float) Math.toRadians(Float.parseFloat(rotation[1]));
                    cube.rotateAngleZ = (float) Math.toRadians(Float.parseFloat(rotation[2]));
//                    System.out.println("shapeName:" + shapeName);
//                    System.out.println("offsetX:" + Float.parseFloat(offset[0]));
//                    System.out.println("offsetY:" + Float.parseFloat(offset[1]));
//                    System.out.println("offsetZ:" + Float.parseFloat(offset[2]));
//                    System.out.println("posX:" + Integer.parseInt(size[0]));
//                    System.out.println("posY:" + Integer.parseInt(size[1]));
//                    System.out.println("posZ:" + Integer.parseInt(size[2]));
//                    System.out.println("textureOffset.W:" + Integer.parseInt(textureOffset[0]));
//                    System.out.println("textureOffset.H:" + Integer.parseInt(textureOffset[1]));
//                    System.out.println("textureDims.W:" + textureDims.getWidth());
//                    System.out.println("textureDims.H:" + textureDims.getHeight());
                    shapesList.add(cube);
                } catch (NumberFormatException e) {
                    FMLLog.warning("Model shape [" + shapeName + "] in " + fileName + " contains malformed integers within its data, ignoring");
                    e.printStackTrace();
                }
            };
        }
        NamedNodeMap folderAttributes = folder.getAttributes();
        Node foldername = folderAttributes.getNamedItem("Name");
        String folderName = null;
        if (foldername != null) {
            folderName = foldername.getNodeValue();
        }
        if (folderName == null) {
            folderName = "folderName #" + (parts.size() + 1);
        }
//        System.out.println("" + foldername);
        parts.put(folderName, shapesList);
    }
    private void bindTexture()
    {
        /*
        if (texture != null)
        {
            if (!textureNameSet)
            {
                try
                {
                    byte[] textureEntry = zipContents.get(texture);
                    if (textureEntry == null)
                    {
                        throw new ModelFormatException("Model " + fileName + " has no such texture " + texture);
                    }
                    
                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(textureEntry));
                    textureName = Minecraft.getMinecraft().renderEngine.allocateAndSetupTexture(image);
                    textureNameSet = true;
                }
                catch (ZipException e)
                {
                    throw new ModelFormatException("Model " + fileName + " is not a valid zip file");
                }
                catch (IOException e)
                {
                    throw new ModelFormatException("Texture for model " + fileName + " could not be read", e);
                }
            }
            
            if (textureNameSet)
            {
                GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureName);
                Minecraft.getMinecraft().renderEngine.resetBoundTexture();
            }
        }
        */
    }
    
    @Override
    public String getType()
    {
        return "tcn";
    }

    @Override
    public void renderAll()
    {
        GL11.glPushMatrix();
		GL11.glRotatef(180,0,1,0);
        GL11.glRotatef(180,0,0,1);
        for (List<ModelRenderer> part : parts.values())
        {
            for (ModelRenderer onepart : part)
            {
                onepart.renderWithRotation(1.0F);
            }
        }
        GL11.glPopMatrix();
    }

    @Override
    public void renderPart(String partName)
    {
        current = null;
        GL11.glPushMatrix();
        GL11.glRotatef(180,0,1,0);
        GL11.glRotatef(180,0,0,1);
        List<ModelRenderer> part = parts.get(partName);
        GL11.glDisable(GL_CULL_FACE);
        if (part != null)
        {
            for (ModelRenderer onepart : part)
            {
                onepart.renderWithRotation(1.0F);
            }
        }
        GL11.glEnable(GL_CULL_FACE);
        GL11.glPopMatrix();
        current = new TechneGroupObject(part);
    }

    @Override
    public void renderOnly(String... groupNames)
    {
        bindTexture();
        for (String groupName : groupNames)
        {
            renderPart(groupName);
        }
    }

    @Override
    public void renderAllExcept(String... excludedGroupNames)
    {
        for (String partnames : parts.keySet())
        {
            for (String excludedgroupName : excludedGroupNames) {
                if (!partnames.equals(excludedgroupName))
                    renderPart(partnames);
            }
        }
    }

    HMGGroupObject current;
    @Override
    public HMGGroupObject renderPart_getInstance() {
        if(current == null){
            return emptyRender;
        }
        return current;
    }

    @Override
    public boolean isReady() {
        return endLoad;
    }
    public String toString(){
        return fileName;
    }
}