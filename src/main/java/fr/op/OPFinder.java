package fr.op;

import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;


public class OPFinder {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static List<BlockPos> firstPosList = new ArrayList<>();
    public static int Y_RADIUS = 70;
    public static int RADIUS = 30;
    public static boolean activated = false;
    public static boolean found = false;
    public static boolean first = false;
    private static BlockPos firstPos = null;
    public static float width = 8;
    public static boolean logged = false;
    @SubscribeEvent
    public void onRenderWorldLast(RenderWorldLastEvent event){OPFIND();}
    public static void OPFIND(){
        EntityPlayer player = mc.thePlayer;
        if (!found){
            if (activated && !first) {
                for (int x = (int) player.posX - RADIUS; x <= (int) player.posX + RADIUS; x++) {
                    for (int y = (int) player.posY - Y_RADIUS; y <= (int) player.posY + Y_RADIUS; y++) {
                        for (int z = (int) player.posZ - RADIUS; z <= (int) player.posZ + RADIUS; z++) {
                            BlockPos pos = new BlockPos(x, y, z);
                            if (!first) {
                                if (mc.theWorld.getBlockState(pos).getBlock() instanceof Block && mc.theWorld.getBlockState(pos).getBlock().getUnlocalizedName().toLowerCase().contains("gold")) {
                                    if (!(mc.theWorld.getBlockState(pos).getBlock() instanceof BlockOre)) {
                                        blockESPBox(pos);
                                        firstPos = pos;
                                        firstPosList.add(pos);
                                        first = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (first) break;
                    }
                if (first) break;
                }
            }
        }
        if (first) {
            int BLOCK_RADIUS = 15;
            for (int x = firstPos.getX() - BLOCK_RADIUS; x <= firstPos.getX() + BLOCK_RADIUS; x++) {
                for (int y = firstPos.getY() - BLOCK_RADIUS; y <= firstPos.getY() + BLOCK_RADIUS; y++) {
                    for (int z = firstPos.getZ() - BLOCK_RADIUS; z <= firstPos.getZ() + BLOCK_RADIUS; z++) {
                        BlockPos pos = new BlockPos(x, y, z);
                        if (mc.theWorld.getBlockState(pos).getBlock() instanceof Block && mc.theWorld.getBlockState(pos).getBlock().getUnlocalizedName().toLowerCase().contains("gold")) {
                            if (!(mc.theWorld.getBlockState(pos).getBlock() instanceof BlockOre)) {
                                blockESPBox(pos);
                                if (!firstPosList.contains(pos)) {
                                    found = true;
                                    if (!logged) {
                                        chatLog(OPCommands.prefix + EnumChatFormatting.GREEN + "One Piece trouvé !");
                                        logged = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public static void blockESPBox(BlockPos blockPos) {

        double x =
                blockPos.getX() - Minecraft.getMinecraft().getRenderManager().viewerPosX;
        double y =
                blockPos.getY() - Minecraft.getMinecraft().getRenderManager().viewerPosY;
        double z =
                blockPos.getZ() - Minecraft.getMinecraft().getRenderManager().viewerPosZ;
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(width);
        GL11.glColor4d(0, 1, 0, 0.15F);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        //drawColorBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glColor4d(1, 1, 0, 0.5F);
        RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0));
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
    }
    private static void chatLog(String str){
        mc.thePlayer.addChatMessage(new ChatComponentText(str));
    }
}
