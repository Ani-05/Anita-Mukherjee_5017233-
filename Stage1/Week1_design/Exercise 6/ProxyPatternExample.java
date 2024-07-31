import java.util.Map;
import java.util.HashMap;

interface Image {
    void display();
}

class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromRemoteServer();
    }

    private void loadFromRemoteServer() {
        System.out.println("Loading image: " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String fileName;
    private static Map<String, RealImage> imageCache = new HashMap<>();

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (imageCache.containsKey(fileName)) {
            realImage = imageCache.get(fileName);
        } else {
            realImage = new RealImage(fileName);
            imageCache.put(fileName, realImage);
        }
        realImage.display();
    }
}

public class ProxyPatternExample {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("img1.jpg");
        Image image2 = new ProxyImage("img2.jpg");

        image1.display();
        System.out.println("");

        image1.display();
        System.out.println("");

        image2.display();
        System.out.println("");

        image2.display();
    }
}