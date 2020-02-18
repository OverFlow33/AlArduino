package org.archLog.projetArduino.elements;

import com.fazecast.jSerialComm.SerialPort;
import org.archLog.projetArduino.models.Arduino;

public class Adapter {
    private SerialPort serialPort;
    private Arduino arduino;

    public Adapter(Arduino arduino) {
        this.arduino = arduino;
    }

    public Adapter() {

    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public Arduino getArduino() {
        return arduino;
    }

    public void setArduino(Arduino arduino) {
        this.arduino = arduino;
    }

    public void sendByte(int id) throws InterruptedException{
        for (SerialPort sp : SerialPort.getCommPorts()) {
            if (sp.getSystemPortName().equals(arduino.getPort())) {
                serialPort = sp;
                break;
            }
        }
        serialPort.setBaudRate(arduino.getBaud());
        serialPort.openPort();
        Thread.sleep(2000);
        byte[] buff = new byte[1];
        buff[0] = (byte)id;
        serialPort.writeBytes(buff, 1);
        serialPort.closePort();
    }
}
