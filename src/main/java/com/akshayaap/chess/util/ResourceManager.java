package com.akshayaap.chess.util;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;

public class ResourceManager {
    private static final int IMAGE_SIZE = 40;
    private static ResourceManager resources = null;
    private final ImageIcon whiteP = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_pawn.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon whiteN = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_knight.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon whiteB = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_bishop.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon whiteR = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_rook.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon whiteQ = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_queen.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon whiteK = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/white_king.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));

    private final ImageIcon blackP = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_pawn.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon blackN = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_knight.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon blackB = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_bishop.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon blackR = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_rook.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon blackQ = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_queen.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon blackK = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/black_king.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private final ImageIcon circle = new ImageIcon(ImageIO.read(Objects.requireNonNull(ResourceManager.class.getResource("/pieces/circle.png"))).getScaledInstance(IMAGE_SIZE, IMAGE_SIZE, Image.SCALE_SMOOTH));
    private ImageIcon IMGS[][] = new ImageIcon[2][6];

    private ResourceManager() throws IOException {
        IMGS = new ImageIcon[][]{
                {blackP, blackN, blackB, blackR, blackQ, blackK},
                {whiteP, whiteN, whiteB, whiteR, whiteQ, whiteK}};
    }

    public static ResourceManager getResourceManager() {
        if (resources == null) {
            try {
                resources = new ResourceManager();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return resources;
    }

    public ImageIcon getWhiteP() {
        return whiteP;
    }

    public ImageIcon getWhiteN() {
        return whiteN;
    }

    public ImageIcon getWhiteB() {
        return whiteB;
    }

    public ImageIcon getWhiteR() {
        return whiteR;
    }

    public ImageIcon getWhiteQ() {
        return whiteQ;
    }

    public ImageIcon getWhiteK() {
        return whiteK;
    }

    public ImageIcon getBlackP() {
        return blackP;
    }

    public ImageIcon getBlackN() {
        return blackN;
    }

    public ImageIcon getBlackB() {
        return blackB;
    }

    public ImageIcon getBlackR() {
        return blackR;
    }

    public ImageIcon getBlackQ() {
        return blackQ;
    }

    public ImageIcon getBlackK() {
        return blackK;
    }

    public ImageIcon getCircle() {
        return circle;
    }

    public ImageIcon getPieceImage(int i, int j) {
        return IMGS[i][j];
    }
}
