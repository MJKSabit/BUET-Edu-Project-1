package github.mjksabit.bueteduproject.DataBase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class Schema {
    private String bgColor = "" ;
    private DefaultCoin defaultCoin;
    private DefaulMatchtStick defaultMatchStick;
    private ArrayList<Elements> elements = new ArrayList<>();
    private String indicatorColor = "";
    private boolean isIndicator;
    private String lineColor = "";
    private double lineOpacity;
    private double transX;
    private double transY;

    public Schema(Map<String,Object>schemaMap)
    {
        Object obj;

        obj = schemaMap.get("bgColor");
        if(obj !=null)
            bgColor = String.valueOf(obj);

        obj = schemaMap.get("defaultCoin");
        if(obj != null)
            defaultCoin = new DefaultCoin((Map<String, Object>) obj);
        obj = schemaMap.get("defaultMatchStick");
        if(obj != null)
            defaultMatchStick = new DefaulMatchtStick((Map<String, Object>) obj);

        ArrayList<Map<String,Object> > list = (ArrayList<Map<String, Object>>) schemaMap.get("elements");
        if(list != null)
        {
            elements = new ArrayList<>();
            for (Map<String,Object> map : list)
                elements.add(new Elements(map));
        }

        obj = schemaMap.get("indicatorColor");
        if(obj !=null)
             indicatorColor = String.valueOf(obj);

        obj = schemaMap.get("isIndicator");
        if(obj != null)
            isIndicator = Boolean.parseBoolean(String.valueOf(obj));
        lineColor = String.valueOf(schemaMap.get("lineColor"));

        obj = schemaMap.get("lineOpacity");
        if(obj!=null)
            lineOpacity = Double.parseDouble(String.valueOf(obj));

        obj = schemaMap.get("transX");
        if(obj!=null)
            transX = Double.parseDouble(String.valueOf(obj));

        obj = schemaMap.get("transY");
        if(obj!=null)
            transY = Double.parseDouble(String.valueOf(obj));

    }

    public String getBgColor() { return bgColor; }
    public DefaultCoin getDefaultCoin() { return defaultCoin; }
    public DefaulMatchtStick getDefaultMatchStick() { return defaultMatchStick; }
    public ArrayList<Elements> getElements() { return elements; }
    public String getIndicatorColor() { return indicatorColor; }
    public boolean isIndicator() { return isIndicator; }
    public String getLineColor() { return lineColor; }
    public double getLineOpacity() { return lineOpacity; }
    public double getTransX() { return transX; }
    public double getTransY() { return transY; }

    public class DefaultCoin {
        private String innerColor = "";
        private boolean isMust;
        private String outerColor = "";
        private int skin ;
        private boolean useSkin ;

        public  DefaultCoin(Map<String,Object> coinMap)
        {
            Object obj;

            obj = coinMap.get("innerColor");
            if(obj!=null)
                innerColor = String.valueOf(obj);

            obj = coinMap.get("isMust");
            if(obj!=null)
                isMust = Boolean.parseBoolean(String.valueOf(obj));

            obj = coinMap.get("outerColor");
            if(obj!=null)
                outerColor = String.valueOf(obj);

            obj = coinMap.get("skin");
            if(obj!=null)
                skin = Integer.parseInt( String.valueOf(obj) );

            obj = coinMap.get("useSkin");
            if(obj!=null)
                useSkin = Boolean.parseBoolean(String.valueOf(obj));
        }

        public String getInnerColor() { return innerColor; }
        public boolean isMust() { return isMust; }
        public String getOuterColor() { return outerColor; }
        public int getSkin() { return skin; }
        public boolean isUseSkin() { return useSkin; }
    }

    public class DefaulMatchtStick {
        private String fillColor = "";
        private boolean isMust;
        private boolean useSkin ;

        public DefaulMatchtStick(Map<String,Object>stickMap)
        {
            Object obj;

            obj = stickMap.get("fillColor");
            if(obj!=null)
                fillColor = String.valueOf(obj);

            obj = stickMap.get("isMust");
            if(obj!=null)
                isMust = Boolean.parseBoolean(String.valueOf(obj));

            obj = stickMap.get("useSkin");
            if(obj!=null)
                useSkin = Boolean.parseBoolean(String.valueOf(obj));
        }

        public String getFillColor() { return fillColor; }
        public boolean isMust() { return isMust; }
        public boolean isUseSkin() { return useSkin; }
    }

    public class Elements{

        private String type = "";

        ///for coin
        private int indX;
        private int indY;
        private String innerColor = "";
        private String outerColor = "";
        private int skin ;
        private String text = "";
        private boolean useSkin ;

       //for matchstick
        private String fillColor = "" ;
        private int indHeadX;
        private int indHeadY;
        private int indTailX;
        private int indTailY;

        //for both
        private boolean cantMove ;
        private boolean isMust;

        public Elements(Map<String,Object> map)
        {
            Object obj ;
            obj = map.get("type");

            if(obj!=null) {
                type = String.valueOf(obj);
                if (type.equals("matchStick"))
                {

                    obj = map.get("fillColor");
                    if(obj!=null)
                        fillColor = String.valueOf(obj);

                    obj = map.get("indHeadX");
                    if(obj!=null)
                        indHeadX = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("indHeadY");
                    if(obj!=null)
                        indHeadY = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("indTailX");
                    if(obj!=null)
                        indTailX = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("indTailY");
                    if(obj!=null)
                        indTailY = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("isMust");
                    if(obj!=null)
                        isMust = Boolean.parseBoolean(String.valueOf(obj));

                    obj = map.get("useSkin");
                    if(obj!=null)
                        useSkin = Boolean.parseBoolean(String.valueOf(obj));

                    obj = map.get("cantMove");
                    if(obj!=null)
                        cantMove = Boolean.parseBoolean(String.valueOf(obj));

                }
                else if(type.equals("coin"))
                {
                    obj = map.get("indX");
                    if(obj!=null)
                        indX = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("indY");
                    if(obj!=null)
                        indY = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("innerColor");
                    if(obj!=null)
                        innerColor = String.valueOf(obj);

                    obj = map.get("isMust");
                    if(obj!=null)
                        isMust = Boolean.parseBoolean(String.valueOf(obj));

                    obj = map.get("outerColor");
                    if(obj!=null)
                        outerColor = String.valueOf(obj);

                    obj = map.get("skin");
                    if(obj!=null)
                        skin = Integer.parseInt( String.valueOf(obj) );

                    obj = map.get("useSkin");
                    if(obj!=null)
                        useSkin = Boolean.parseBoolean(String.valueOf(obj));

                    obj = map.get("splash_logo");
                    if(obj!=null)
                        text = String.valueOf(obj);
                }
            }
        }

        public String getType() { return type; }

        public int getIndX() { return indX; }
        public int getIndY() { return indY; }
        public String getInnerColor() { return innerColor; }
        public String getOuterColor() { return outerColor; }
        public int getSkin() { return skin; }
        public String getText() { return text; }
        public boolean isUseSkin() { return useSkin; }
        public String getFillColor() { return fillColor; }
        public int getIndHeadX() { return indHeadX; }
        public int getIndHeadY() { return indHeadY; }
        public int getIndTailX() { return indTailX; }
        public int getIndTailY() { return indTailY; }
        public boolean isCantMove() { return cantMove; }
        public boolean isMust() { return isMust; }
    }


}
