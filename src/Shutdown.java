import java.io.IOException;

public class Shutdown {

    public enum OS {
        WINDOWS, LINUX, MAC, UNKNOWN
    }

    public static OS getOS() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return OS.WINDOWS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            return OS.LINUX;
        } else if (osName.contains("mac")) {
            return OS.MAC;
        } else {
            return OS.UNKNOWN;
        }
    }

    public int hora (int temp) {
        int time = temp * 60 * 60;
        return time;
    }

    public int minuto (int temp) {
        int time = temp * 60;
        return time;
    }

    /**
     * Retorna o comando de desligamento de acordo com o Sistema Operacional.
     */
    public String getShutdownCommand(int temp, boolean isHora) {
        OS os = getOS();
        int minutos = isHora ? temp * 60 : temp;
        int segundos = isHora ? temp * 3600 : temp * 60;

        switch (os) {
            case WINDOWS:
                return "shutdown -s -t " + segundos;
            case LINUX:
            case MAC:
                if (minutos <= 0) {
                    return "shutdown -h now";
                }
                return "shutdown -h +" + minutos;
            default:
                return "shutdown -s -t " + segundos;
        }
    }

    /**
     * Retorna a instrução de como cancelar o desligamento no terminal do OS atual.
     */
    public String getCancelInstruction() {
        OS os = getOS();
        switch (os) {
            case WINDOWS:
                return "vá no cmd e digite 'shutdown -a'.";
            case LINUX:
            case MAC:
                return "vá no terminal e digite 'shutdown -c'.";
            default:
                return "vá no terminal/cmd e digite o comando de cancelamento.";
        }
    }

    /**
     * Executa o desligamento agendado no sistema operacional.
     */
    public void agendarDesligamento(int temp, boolean isHora) throws IOException {
        String command = getShutdownCommand(temp, isHora);
        OS os = getOS();
        
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            if ((os == OS.LINUX || os == OS.MAC) && command.startsWith("shutdown")) {
                Runtime.getRuntime().exec("/usr/sbin/" + command);
            } else {
                throw e;
            }
        }
    }

    /**
     * Cancela um desligamento agendado.
     */
    public void cancelarDesligamento() throws IOException {
        OS os = getOS();
        String command;
        switch (os) {
            case WINDOWS:
                command = "shutdown -a";
                break;
            case LINUX:
            case MAC:
                command = "shutdown -c";
                break;
            default:
                command = "shutdown -a";
                break;
        }

        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            if ((os == OS.LINUX || os == OS.MAC) && command.startsWith("shutdown")) {
                Runtime.getRuntime().exec("/usr/sbin/" + command);
            } else {
                throw e;
            }
        }
    }
}

