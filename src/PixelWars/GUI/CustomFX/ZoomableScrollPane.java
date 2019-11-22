package PixelWars.GUI.CustomFX;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class ZoomableScrollPane extends ScrollPane {

    private final double minZoomScale = 2 / 3., maxZoomScale = 5 / 3.;

    private PanAndZoomPane panAndZoomPane;

    public ZoomableScrollPane()
    {
        panAndZoomPane=new PanAndZoomPane();

        setContent(panAndZoomPane);

        panAndZoomPane.toBack();

        addEventFilter(MouseEvent.MOUSE_CLICKED, panAndZoomPane.getOnMouseClickedEventHandler());
        addEventFilter(MouseEvent.MOUSE_PRESSED, panAndZoomPane.getOnMousePressedEventHandler());
        addEventFilter(MouseEvent.MOUSE_DRAGGED, panAndZoomPane.getOnMouseDraggedEventHandler());
        addEventFilter(ScrollEvent.ANY, panAndZoomPane.getOnScrollEventHandler());
    }

    public ZoomableScrollPane(Node content) {
        this();
        setInside(content);
    }

    public void setInside(Node content) {
        ObservableList<Node> panAndZoomPane_Children = panAndZoomPane.getChildren();
        panAndZoomPane_Children.clear();
        Group g = new Group(content);
        long l=System.currentTimeMillis();
        panAndZoomPane_Children.add(g);
        System.out.println("ADDING TO CHILDREN "+(System.currentTimeMillis()-l));

    }

    public Node getInside() {
        ObservableList<Node> panAndZoomPane_Children = panAndZoomPane.getChildren();
        return panAndZoomPane_Children.isEmpty() ? null : ((Group)panAndZoomPane_Children.get(0)).getChildren().get(0);
    }

    private class PanAndZoomPane extends Pane {

        private static final double DEFAULT_DELTA = 1 / 6.;
        private DoubleProperty myScale = new SimpleDoubleProperty(1.0);
        private DoubleProperty deltaY = new SimpleDoubleProperty(0.0);

        private PanAndZoomPane() {
            scaleXProperty().bind(myScale);
            scaleYProperty().bind(myScale);
        }

        private double getScale() {
            return myScale.get();
        }

        private void setPivot(double x, double y, double scale) {
            setTranslateX(getTranslateX() - x);
            setTranslateY(getTranslateY() - y);
            myScale.setValue(scale);

        }

        private void resetZoom() {
            double scale = 1.0d;

            double x = getTranslateX();
            double y = getTranslateY();

            setPivot(x, y, scale);
        }

        private void setDeltaY(double dY) {
            deltaY.set(dY);
        }

        double mouseAnchorX;
        double mouseAnchorY;

        double translateAnchorX;
        double translateAnchorY;

        public EventHandler<MouseEvent> getOnMouseClickedEventHandler() {
            return onMouseClickedEventHandler;
        }

        public EventHandler<MouseEvent> getOnMousePressedEventHandler() {
            return onMousePressedEventHandler;
        }

        public EventHandler<MouseEvent> getOnMouseDraggedEventHandler() {
            return onMouseDraggedEventHandler;
        }

        public EventHandler<ScrollEvent> getOnScrollEventHandler() {
            return onScrollEventHandler;
        }

        private EventHandler<MouseEvent> onMousePressedEventHandler = new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {

                mouseAnchorX = event.getX();
                mouseAnchorY = event.getY();

                translateAnchorX = panAndZoomPane.getTranslateX();
                translateAnchorY = panAndZoomPane.getTranslateY();

            }

        };

        private EventHandler<MouseEvent> onMouseDraggedEventHandler = new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                double translationX = translateAnchorX + event.getX() - mouseAnchorX;
                double translationY = translateAnchorY + event.getY() - mouseAnchorY;
                panAndZoomPane.setTranslateX(translationX);
                panAndZoomPane.setTranslateY(translationY);

                event.consume();
            }
        };

        private EventHandler<ScrollEvent> onScrollEventHandler = new EventHandler<ScrollEvent>() {

            @Override
            public void handle(ScrollEvent event) {
                double scale = panAndZoomPane.getScale();
                int direction = event.getDeltaY() > 0 ? 1 : -1;
                panAndZoomPane.setDeltaY(event.getDeltaY());
                scale += direction * DEFAULT_DELTA;

                if (scale > minZoomScale && scale < maxZoomScale) {
                    panAndZoomPane.setPivot(0, 0, scale);
                }
                event.consume();
            }
        };

        private EventHandler<MouseEvent> onMouseClickedEventHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    if (event.getClickCount() == 2) {
                        panAndZoomPane.resetZoom();
                    }
                }
            }
        };
    }
}