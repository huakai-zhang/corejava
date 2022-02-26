package corejava.chapter8.section6;

/**
 * @author Spring Zhang
 * @date 2019/12/3 10:51
 */
public class Singleton<T> {
    /*private static T singleInstance; //Error

    public static T getSingleInstance() { //Error
        if (singleInstance == null) {
            return singleInstance;
        }
    }*/

    public static <T extends Throwable> void doWork(T t) throws T {
        try {

        } catch (Throwable realCause) {
            t.initCause(realCause);
            throw t;
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Throwable> void throwAs(Throwable e) throws T {
        throw (T) e;
    }
}
