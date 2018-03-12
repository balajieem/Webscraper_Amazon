import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;

public class AmazonAsinLevel {

    static Logger log = Logger.getLogger(AmazonAsinLevel.class.getName());

    public static void main(String[] args) throws IOException {


        //**** read and get keywords from Gsheet*****//
        keywordsRead keywordsReadObject = new keywordsRead();
        Set<String> uniqueKeywords =keywordsReadObject.getUniqueKeywords();


        //**** input keywords from Gsheet and get URLs/ASIN# for keywords*****//

        //String keyword = "insect repellent";
        for (String keyword:uniqueKeywords) {

            jsoupSubClass jsoupSubClassObject = new jsoupSubClass();
            jsoupSubClassObject.sClass(keyword);

            jsoupSubClass1 jsoupSubClass1Object = new jsoupSubClass1();
            jsoupSubClass1Object.sClass1(keyword);

            jsoupSubClass2 jsoupSubClass2Object = new jsoupSubClass2();
            jsoupSubClass2Object.sClass2(keyword);

        }

        //****read asin from Gsheet and input it to gather individual asin data*****//

        AsinSubclass AsinSubClassObject = new AsinSubclass();
        asinRead asinReadObject = new asinRead();

        Set<String> uniqueAsins = asinReadObject.getUniqueAsins();

        for (String asin:uniqueAsins) {

            AsinSubClassObject.sClass(asin);

        }

    }
}
