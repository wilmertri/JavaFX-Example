package persistence;

import com.example.javafxdemo.InsertUserApplication;

import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class FileManage {
    private String fileName;
    public FileManage(String fileName){
        this.fileName = fileName;
    }
    public ArrayList<String> getDataOfFile(){
        ArrayList<String> lines = null;
        try {
            File file = this.getFile();
            if(file.exists()){
                lines = new ArrayList<>();
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null){
                    lines.add(line);
                }
                br.close();
            }
            else{
                JOptionPane.showMessageDialog(null, "The file wasn't found!");
            }
        }catch (FileNotFoundException ex){
            ex.printStackTrace(System.out);
        }catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return lines;
    }

    public Boolean insertDataInFile(String line){
        File file = this.getFile();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            PrintWriter pw = new PrintWriter(bw);
            pw.println(line);
            pw.flush();
            pw.close();
            return true;
        }catch (IOException e) {
            e.printStackTrace(System.out);
        }

        return false;
    }

    public Boolean updateDataInFile(String oldLine, String newLine){
        ArrayList<String> lines = this.getDataOfFile();
        if (lines != null){
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).equals(oldLine)){
                    lines.set(i, newLine);
                    this.writeLinesToFile(lines);
                    System.out.println("Linea actualizada exitosamente");
                    return true;
                }
            }
            System.out.println("La linea a actualizar no se encontró en el archivo");
        }
        return false;
    }

    public void deleteDataInFile(String lineToDelete){
        ArrayList<String> lines = this.getDataOfFile();
        if(lines != null){
            if(lines.remove(lineToDelete)){
                writeLinesToFile(lines);
                System.out.println("Linea eliminada exitosamente");
            }else{
                System.out.println("La linea a eliminar no se encontró en el archivo");
            }
        }
    }

    private File getFile(){
        return new File("files/" + this.fileName);
    }

    private void writeLinesToFile(ArrayList<String> lines){
        try {
            PrintWriter output = new PrintWriter(new FileWriter(this.getFile()));
            for (String line : lines){
                output.println(line);
            }
            output.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
