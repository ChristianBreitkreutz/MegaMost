package de.christianbreitkreutz;

public class MegaMostExeption extends Exception {

    public MegaMostExeption(final String message, final int status, final String headers, final String response) {
        super(
                String.format(
                        "The message: \"%s\" could not be sent.%nstatus: %d%nresponse headers: %s%nresponse: %s"
                        ,message, status, headers, response
                )
        );
    }


    private static final long serialVersionUID = 2866675065841656039L;
}
