package brut.androlib.src;

import java.util.logging.Logger;

public class DecoderLogger {
    private final static Logger LOGGER = Logger.getLogger("---");

    private static DecoderLogger fakerLogger= null;

    IFakerLogerCallBack fakerLogerCallBack;

    public IFakerLogerCallBack getFakerLogerCallBack() {
        return fakerLogerCallBack;
    }

    public void setFakerLogerCallBack(IFakerLogerCallBack fakerLogerCallBack) {
        this.fakerLogerCallBack = fakerLogerCallBack;
    }

    private DecoderLogger(){

    }
    public static DecoderLogger getFakerLogger(){
        if(fakerLogger==null){
            fakerLogger = new DecoderLogger();
        }
        return fakerLogger;
    }
    public void info(String log){
        if(fakerLogerCallBack!=null){
            fakerLogerCallBack.transLog(log);
        }else {
            LOGGER.info(log);
        }
    }

    public void warning(String log){
        if(fakerLogerCallBack!=null){
            fakerLogerCallBack.transLog(log);
        }else {
            LOGGER.warning(log);
        }
    }
    public void fine(String fine){
        if(fakerLogerCallBack!=null){
            fakerLogerCallBack.transLog(fine);
        }else {
            LOGGER.fine(fine);
        }
    }
    public void severe(String severe){
        if(fakerLogerCallBack!=null){
            fakerLogerCallBack.transLog(severe);
        }else {
            LOGGER.fine(severe);
        }
    }
}
