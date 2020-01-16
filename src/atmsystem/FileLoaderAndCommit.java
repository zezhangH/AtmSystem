package atmsystem;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;

public class FileLoaderAndCommit {
    private String filename = null;

    public FileLoaderAndCommit(String filename) {
        this.filename = filename;
    }

    public HashMap<String, User> loadFile() {
        HashMap<String, User> userBox = new HashMap<>();
        FileReader fileReader = null;
        BufferedReader br = null;
        try {
            File file = new File(filename);
            fileReader = new FileReader(file);
            br = new BufferedReader(fileReader);
            String value = br.readLine();
            while (value != null) {
                String[] userValue = value.split("-");
                User user = new User(userValue[0], userValue[1], Float.parseFloat(userValue[2]));
                userBox.put(user.getName(), user);
                value = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userBox;
    }

    public void commit(HashMap<String, User> userBox) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            File file = new File(filename);
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            Iterator<String> names = userBox.keySet().iterator();
            while (names.hasNext()) {
                String name = names.next();
                User user = userBox.get(name);
                StringBuilder builder = new StringBuilder(user.getName());
                builder.append("-");
                builder.append(user.getPassword());
                builder.append("-");
                builder.append(user.getAblance());
                bufferedWriter.write(builder.toString());
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
