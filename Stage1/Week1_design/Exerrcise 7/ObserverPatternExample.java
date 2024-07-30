import java.util.List;
import java.util.ArrayList;

public class ObserverPatternExample {

    interface Stock {
        void registerObserver(Observer observer);
        void deregisterObserver(Observer observer);
        void notifyObservers();
    }

    static class StockMarket implements Stock {
        private List<Observer> observers;
        private String stockName;
        private double stockPrice;

        public StockMarket(String stockName, double stockPrice) {
            this.observers = new ArrayList<>();
            this.stockName = stockName;
            this.stockPrice = stockPrice;
        }

        @Override
        public void registerObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void deregisterObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            for (Observer observer : observers) {
                observer.update(stockName, stockPrice);
            }
        }

        public void setStockPrice(double stockPrice) {
            this.stockPrice = stockPrice;
            notifyObservers();
        }
    }

    interface Observer {
        void update(String stockName, double stockPrice);
    }

    static class MobileApp implements Observer {
        private String appName;

        public MobileApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String stockName, double stockPrice) {
            System.out.println(appName + " received update: " + stockName + " is now " + stockPrice);
        }
    }

    static class WebApp implements Observer {
        private String appName;

        public WebApp(String appName) {
            this.appName = appName;
        }

        @Override
        public void update(String stockName, double stockPrice) {
            System.out.println(appName + " received update: " + stockName + " is now " + stockPrice);
        }
    }

    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket("AAPL", 150.00);

        Observer mobileApp = new MobileApp("MobileApp");
        Observer webApp = new WebApp("WebApp");

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setStockPrice(155.00);
        stockMarket.setStockPrice(160.00);

        stockMarket.deregisterObserver(webApp);
        stockMarket.setStockPrice(165.00);
    }
}
