package Exceptions;

public class PreaMulteCursuriException extends RuntimeException {
    public PreaMulteCursuriException()
    {
        super("Nu mai sunt locuri disponibile");
    }
}
