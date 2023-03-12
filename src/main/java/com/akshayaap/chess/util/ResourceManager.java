package com.akshayaap.chess.util;

import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.network.Client;
import com.akshayaap.chess.network.RetrofitClient;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class ResourceManager {
    private RetrofitClient retrofitClient = null;

    public Client getWebsock() {
        return websock;
    }

    public void setWebsock(Client websock) {
        this.websock = websock;
    }

    private Client websock = null;

    public RetrofitClient getRetrofitClient() {
        return retrofitClient;
    }

    public void setRetrofitClient(RetrofitClient retrofitClient) {
        this.retrofitClient = retrofitClient;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    private Logable logger = null;

    private Client client = null;
    private String serverURI = null;
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

    private ResourceManager() throws IOException, ExecutionException, InterruptedException {
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
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
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


    public String getServerURI() {
        return this.serverURI;
    }

    public void setServerURI(String serverURI) {
        this.serverURI = serverURI;
    }

    public void connect(String uri) throws URISyntaxException, ExecutionException, InterruptedException {
        this.serverURI = uri;
        this.retrofitClient = new RetrofitClient("http://" + uri);
        this.websock.connectToServer("ws://" + uri);
        logger.log("Successfully connected to server");
    }

    public void setLogger(Logable logger) {
        this.logger = logger;
    }

    public Logable getLogger() {
        return logger;
    }
}
