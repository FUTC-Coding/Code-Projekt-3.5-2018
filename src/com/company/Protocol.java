package com.company;

import java.lang.String;
import java.util.ArrayList;

public class Protocol {

    private byte client_id;
    private byte[][] big_board;
    private byte[][] cells;
    private byte[] active_field;
    private byte id;
    private byte[] coordinates;

    public byte getClient_id(){
        return client_id;
    }
    public byte[][] getBig_board(){
        return big_board;
    }
    public byte[][] getCells(){
        return cells;
    }
    public byte[] getActive_field(){
        return active_field;
    }
    public byte getId(){
        return  id;
    }
    public byte[] getCoordinates(){
        return  coordinates;
    }

    public void getByteArray(String information){
        byte[] information_byte = information.getBytes();
        for (int i = 0; i < information_byte.length; i++) {
            if (information_byte[i] == 0xFF) {

            } else {

            }
        }
    }
}
