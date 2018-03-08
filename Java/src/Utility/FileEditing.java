package Utility;

import java.io.*;

public class FileEditing {
    /*String*/
    public void export(String filename, String save) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             PrintWriter writer = new PrintWriter(fos);) {
            writer.println("" + save);
        } catch (IOException ioe) {
            System.out.println("Problem saving file " + filename);
            ioe.printStackTrace();
        }
    }

    public String Import(String filename) {
        try (FileInputStream fis = new FileInputStream(filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis));) {
            String Load;
            while ((Load = reader.readLine()) != null) {
                return Load;
            }
        } catch (IOException ioe) {
            System.out.println("Problem saving file " + filename);
            ioe.printStackTrace();
        }
        return null;
    }

    /*bytes*/
    public void SavebitFile(byte[] byts, String path) {
        try (FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(byts);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] LoadbitFile(String path) {
        byte[] byts;
        try (FileInputStream fis = new FileInputStream(path)) {
            return byts = fis.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byts = null;
    }
}
