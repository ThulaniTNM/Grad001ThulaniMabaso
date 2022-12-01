package com.eviro.assessment.grad001.thulaniMabaso.Grad001ThulaniMabaso;

import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class AccountProfileService implements FileParser {
    @Autowired
    private AccountProfileRepository repository;

    String name ="";
    String base64StringImageFormat ="";
    String base64String ="";
    // save cvs data to db

    public void saveLoadAccountProfile(){
        parseCSV(new File("src/main/resources/csv/1664806858150-GraduateDev_AssessmentCsv_v2.csv"));
    }

    @Override
    public void parseCSV(File csvFile) {
        String line;

        try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while((line = br.readLine()) != null){
                String[] csvLineText = line.split(",");

                name = csvLineText[0];
                String surname = csvLineText[1];
                base64StringImageFormat = csvLineText[2].split("/")[1];
                base64String = csvLineText[3];

                File fileImage = convertCSVDataToImage(base64String);
                URI resourceLocation = createImageLink(fileImage);

                AccountProfile accountProfile = new AccountProfile();
                accountProfile.setName(name);
                accountProfile.setSurname(surname);
                accountProfile.setHttpImageLink(resourceLocation.getPath());

                repository.save(accountProfile);
            }
        }
        catch (IOException e){
            // log possible error
            e.printStackTrace();
        }
    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        byte[] base64ContentByte = DatatypeConverter.parseBase64Binary(base64ImageData);

        File imageFile = new File("src/main/resources/static/"+name+"."+base64StringImageFormat);
        try(OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(imageFile))) {
            outputStream.write(base64ContentByte);
        }catch (IOException e){
            e.printStackTrace();
        }

        return imageFile;
    }

    @Override
    public URI createImageLink(File fileImage) {
        return fileImage.toURI();
    }

    public String getResourcePath(String name, String surname){
        String resourcePath = "";
        List<AccountProfile> accountProfiles = (List<AccountProfile>) repository.findAll();

        for(AccountProfile ap : accountProfiles){
            if (name.equals( ap.getName()) && surname.equals(ap.getSurname())){
                resourcePath = ap.getHttpImageLink();
            }
        }

        return resourcePath;
    }

    public List<AccountProfile> getAll(){
        return (List<AccountProfile>) repository.findAll();
    }
}
