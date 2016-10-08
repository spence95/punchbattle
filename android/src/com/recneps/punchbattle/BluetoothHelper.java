package com.recneps.punchbattle;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by spence95 on 2/28/2016.
 */
public class BluetoothHelper {
    BluetoothAdapter mBluetoothAdapter;
    Activity app;
    ArrayList<PairedDevice> pairedDevices;

    private final static int REQUEST_ENABLE_BT = 1;
    private final static int REQUEST_DISCOVER = 2;

    private DeviceUuidFactory uuidFactory;


    public BluetoothHelper(Activity app) {
        this.app = app;
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
        }

        pairedDevices = new ArrayList<PairedDevice>();
        uuidFactory = new DeviceUuidFactory(app);
    }

    public String connect(){

        //check if bluetooth is enabled, if not ask
        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            app.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);

        }

        return "bleh";
    }

    private void getPairedDevices(){
        Set<BluetoothDevice> pds = mBluetoothAdapter.getBondedDevices();
        // If there are paired devices
        if (pairedDevices.size() > 0) {
            // Loop through paired devices
            for (BluetoothDevice device : pds) {
                // Add the name and address to an array adapter to show in a ListView
                PairedDevice pd = new PairedDevice(device.getName(), device.getAddress());
                pairedDevices.add(pd);
            }
        }
    }

    private void enableDiscoverability(){
        Intent discoverableIntent = new
                Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
        app.startActivityForResult(discoverableIntent, REQUEST_DISCOVER);
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_ENABLE_BT) {
            if(resultCode == Activity.RESULT_OK){
                //bluetooth is now enabled
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
        else if(requestCode == REQUEST_DISCOVER){
            if(resultCode == Activity.RESULT_OK){
                //now discoverable
                String result=data.getStringExtra("result");
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }

    private class AcceptThread extends Thread {
        private final BluetoothServerSocket mmServerSocket;

        public AcceptThread() {
            // Use a temporary object that is later assigned to mmServerSocket,
            // because mmServerSocket is final
            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(getName(), uuidFactory.getDeviceUuid());
            } catch (IOException e) { }
            mmServerSocket = tmp;
        }

        public void run() {
            BluetoothSocket socket = null;
            // Keep listening until exception occurs or a socket is returned
            while (true) {
                try {
                    socket = mmServerSocket.accept();
                } catch (IOException e) {
                    break;
                }
                // If a connection was accepted
                if (socket != null) {
                    // Do work to manage the connection (in a separate thread)
                    //manageConnectedSocket(socket);
                    //mmServerSocket.close();
                    break;
                }
            }
        }

        /** Will cancel the listening socket, and cause the thread to finish */
        public void cancel() {
            try {
                mmServerSocket.close();
            } catch (IOException e) { }
        }
    }


}
