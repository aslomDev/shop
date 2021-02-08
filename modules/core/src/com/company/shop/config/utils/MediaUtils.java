package com.company.shop.config.utils;

import com.company.shop.entity.Product;
import com.company.shop.entity.ProductContent;
import com.company.shop.service.ProductContentService;
import com.company.shop.service.ProductService;
import com.company.shop.service.UserService;
import com.haulmont.cuba.core.global.FileLoader;
import com.haulmont.cuba.core.global.FileStorageException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;

import javax.inject.Inject;
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedList;
import java.util.List;

@Component
public class MediaUtils {
    @Inject private ProductContentService productContentService;
    @Inject private FileLoader fileLoader;
    @Inject private ProductService productService;
    @Inject private UserService userService;
    @Inject private Lang lang;

    // methods

    /**
     * SendMedia
     * @param update
     * @return List = album
     */
    public SendMediaGroup productAndContent(Update update){
        SendMediaGroup sendMediaGroup = new SendMediaGroup();
        List<InputMedia> inputMediaList = new LinkedList<>();
        Message message = update.getCallbackQuery().getMessage();
        String data = update.getCallbackQuery().getData();
        if (data.startsWith("category#Product")){
            String id = data.substring(16);
            int ids = Integer.parseInt(id);
                userService.createCategory(lang.getUser(update), ids);
        }
        if (data.startsWith("category#next")){
            List<Product> productList = productService.getProductOffset(lang.getCategory(update), lang.getItem(update));
                for (Product product : productList){
                    List<ProductContent> productContentList = productContentService.getProductContent(product.getId());
                    int caption = 0;
                    int item = 1;
                    for (ProductContent productContent : productContentList){
                        item=item + 1;
                        if (item < 6){
                            try {
                                caption=caption + 1;
                                InputMediaPhoto inputMedia = new InputMediaPhoto();
                                inputMedia.setMedia(fileLoader.openStream(productContent.getFileProduct()), productContent.getFileProduct().getName());
                                if (productContentList.size() == caption){
                                    List<Product> pCaption = productService.getProduct(lang.getCategory(update));
                                    for (Product product1 : pCaption){
                                        if (product.getId().equals(product1.getId())){
                                            if (lang.getLang(update).equals("ru")) {
                                                inputMedia.setCaption(product1.getDescriptionRu());
                                            }else  if (lang.getLang(update).equals("uz")) {
                                                inputMedia.setCaption(product1.getDescriptionUz());
                                            }
                                        }
                                    }
                                }
                                inputMediaList.add(inputMedia);
                            }catch (FileStorageException e){
                                e.printStackTrace();
                            }
                        }
                    }

                }

        } else {
            List<Product> productList = productService.getProductOffset(lang.getCategory(update),lang.getItem(update));
            if (productList.size() == 1){
                userService.createItem(lang.getUser(update), 1);
                for (Product product : productList){
                    List<ProductContent> productContentList = productContentService.getProductContent(product.getId());
                    int caption = 0;
                    int item = 1;
                    for (ProductContent productContent : productContentList){
                        item=item + 1;
                        if (item < 6){
                            try {
                                caption=caption + 1;
                                InputMediaPhoto inputMedia = new InputMediaPhoto();
                                inputMedia.setMedia(fileLoader.openStream(productContent.getFileProduct()), productContent.getFileProduct().getName());
                                if (productContentList.size() == caption){
                                    List<Product> pCaption = productService.getProduct(lang.getCategory(update));
                                    for (Product product1 : pCaption){
                                        if (product.getId().equals(product1.getId())){
                                            if (lang.getLang(update).equals("ru")) {
                                                inputMedia.setCaption(product1.getDescriptionRu());
                                            }else  if (lang.getLang(update).equals("uz")) {
                                                inputMedia.setCaption(product1.getDescriptionUz());
                                            }
                                        }
                                    }
                                }
                                inputMediaList.add(inputMedia);
                            }catch (FileStorageException e){
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }
        sendMediaGroup.setChatId(message.getChatId().toString());
        if (!inputMediaList.isEmpty()){
            sendMediaGroup.setMedias(inputMediaList);
            return sendMediaGroup;
        }
        return null;

    }

    /**
     * проверайть продакта
     * @param update
     * @return true content, false button
     */
    public boolean outSize(Update update){
        String data = update.getCallbackQuery().getData();
        if (data.startsWith("category#Product")) {
            String id = data.substring(16);
            int ids = Integer.parseInt(id);
                userService.createCategory(lang.getUser(update), ids);

        }
        List<Product> productList = productService.getProduct(lang.getCategory(update));
        if (productList.size() == 0){
            return true;
        }
        return false;
    }

    /**
     * проверайть контент продакта
     * @param update
     * @return true content, false button
     */
    public boolean contentOutSize(Update update){
        String data = update.getCallbackQuery().getData();
        if (data.startsWith("category#Product")) {
            String id = data.substring(16);
            int ids = Integer.parseInt(id);
                userService.createCategory(lang.getUser(update), ids);
        }
        List<Product> productList = productService.getProduct(lang.getCategory(update));
        for (Product product : productList){
            List<ProductContent> productContents = productContentService.getProductContent(product.getId());
            if (productContents.size() > 0){
                return true;
            }
        }
        return false;
    }

    /**
     * проверайть контент продакта по индекс = 0
     * что он оден или несколкьло
     * оден true, несколкьло false
     * @param update
     * @return true sendPhoto, false sendMediaGroup
     */
    public boolean contetn1(Update update) {
        List<Product> productList = productService.getProductOffset(lang.getCategory(update), 0);
        if (productList.size() == 1) {
            for (Product product : productList) {
                List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                if (productContents.size() == 1) {
                    userService.createItem(lang.getUser(update), 0);
                    userService.createIsOneTrueContent(lang.getUser(update));
                    return true;
                } else if (productContents.size() > 1) {
                    userService.createItem(lang.getUser(update), 0);
                    userService.createIsOneFalseContent(lang.getUser(update));
                }
            }
        }
        return false;
    }


    /**
     * проверайть контент продакта по остальные индексу
     * что он оден или несколкьло
     * записывают createIsOneTrueContent, createIsOneFalseContent
     * также записывают item по индексу что би получать контент
     * createIsOneTrueContent sendPhoto, createIsOneFalseContent sendMediaGroup
     * @param update
     */
    public void pContentLoop(Update update){
        String data = update.getCallbackQuery().getData();
        int item = 1;
            List<Product> products = productService.getProduct(lang.getCategory(update));
            for (Product product : products){
                if (item == 1) {
                    item=item + 1;
                    if (!data.startsWith("category#next")){
                        List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                        if (!productContents.isEmpty()) {
                            userService.createItem(lang.getUser(update), 0);
                            break;
                        }
                    }
                }else if (item > 1){
                    List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                    if (productContents.size() == 1){
                        userService.createIsOneTrueContent(lang.getUser(update));
                        if (!data.startsWith("category#next")){
                            userService.createItem(lang.getUser(update), item - 1);
                        }
                        break;
                    }else if (productContents.size() > 1){
                        userService.createIsOneFalseContent(lang.getUser(update));
                        if (!data.startsWith("category#next")){
                            userService.createItem(lang.getUser(update), item - 1);
                        }
                        break;
                    }
                    item=item + 1;
                }
            }
    }

    /**
     * проверайть контент продакта по нажатие кнопка еше
     * что он оден или несколкьло
     * записывают createIsOneTrueContent, createIsOneFalseContent
     * createIsOneTrueContent sendPhoto, createIsOneFalseContent sendMediaGroup
     * @param update
     */
    public void pContentLoopNext(Update update){
        List<Product> products = productService.getProductOffset(lang.getCategory(update), lang.getItem(update));
        for (Product product : products){
            List<ProductContent> productContents = productContentService.getProductContent(product.getId());
            if (productContents.size() == 1){
                userService.createIsOneTrueContent(lang.getUser(update));
            }else if (productContents.size() > 1){
                userService.createIsOneFalseContent(lang.getUser(update));
            }
        }
    }

    /**
     * получать контент продакта по нажатие кнопка еше
     * записывают item по индексу что би получать контент
     * @param update
     */
    public void pContentNext(Update update){
        List<Product> products = productService.getProduct(lang.getCategory(update));
        int sizeAll = products.size();
        int sizeOffset = lang.getItem(update);
        sizeAll=sizeAll - sizeOffset;
        int value = sizeAll;
        int item = 1;
        List<Product> productsValue = productService.getProductOut(lang.getCategory(update), lang.getItem(update), value);
        for (Product product : productsValue) {
            item=item + 1;
            if (item == 2) {
                /// пустой! надо пропустить 0
            } else {
                List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                    if (productContents.size() >= 1) {
                    userService.createItem(lang.getUser(update), lang.getItem(update) + item - 2);
                        break;
                }
            }
        }
    }


    /**
     * Button
     * проверайть контент продакта по следующее индексу
     * если есть записывают true что би получать button(еше), если нет false
     * @param update
     * true button(назад, еше), false button(назад)
     */
    public void outButtonProduct(Update update){
        List<Product> products = productService.getProduct(lang.getCategory(update));
        int sizeAll = products.size();
        if (lang.getItem(update).equals(0)){
            int sizeOffset = 1;
            sizeAll=sizeAll - sizeOffset;
            int value = sizeAll;

            int item = 1;
            List<Product> productsValue = productService.getProductOut(lang.getCategory(update), lang.getItem(update), value);
            for (Product product : productsValue) {
                item=item + 1;
                if (item == 2) {
                    /// пустой! надо пропустить 0
                    userService.createOutCFalse(lang.getUser(update));
                } else {
                    List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                    if (productContents.size() == 0) {
                        userService.createOutCFalse(lang.getUser(update));
                    } else if (productContents.size() >= 1) {
                        userService.createOutCTrue(lang.getUser(update));
                        break;
                    }
                }
            }
        }else {
            int sizeOffset = lang.getItem(update);
            sizeAll=sizeAll - sizeOffset;
            int value = sizeAll;
            int item = 1;
            List<Product> productsValue = productService.getProductOut(lang.getCategory(update), lang.getItem(update), value);
            for (Product product : productsValue) {
                item=item + 1;
                if (item == 2) {
                    /// пустой! надо пропустить 0
                    userService.createOutCFalse(lang.getUser(update));
                } else {
                    List<ProductContent> productContents = productContentService.getProductContent(product.getId());
                    if (productContents.size() == 0) {
                        userService.createOutCFalse(lang.getUser(update));
                    } else if (productContents.size() >= 1) {
                        userService.createOutCTrue(lang.getUser(update));
                        break;
                    }
                }
            }
        }


    }


    /**
     * SendPhoto
     * @param update
     * @return photo
     */
    public SendPhoto sendMediaPhoto(Update update){
        SendPhoto sendPhoto = new SendPhoto();
        InputFile inputFile = new InputFile();
        Message message = update.getCallbackQuery().getMessage();
        List<Product> productList = productService.getProductOffset(lang.getCategory(update), lang.getItem(update));

       for (Product product : productList){
           List<ProductContent> productContentList = productContentService.getProductContent(product.getId());
          if (productContentList.size() == 1){
              for (ProductContent pC : productContentList){
                  try {
                      inputFile.setMedia(fileLoader.openStream(pC.getFileProduct()), pC.getFileProduct().getName());
                      sendPhoto.setPhoto(inputFile);
                      List<Product> products = productService.getProduct(lang.getCategory(update));
                      for (Product product2 : products){
                          if (product.getId().equals(product2.getId())){
                              if (lang.getLang(update).equals("uz")){
                                  sendPhoto.setCaption(product.getDescriptionUz());
                              }else if (lang.getLang(update).equals("ru")){
                                  sendPhoto.setCaption(product.getDescriptionRu());
                              }
                          }
                      }

                  } catch (FileStorageException e) {
                      e.printStackTrace();
                  }
              }
          }
       }

        sendPhoto.setChatId(message.getChatId().toString());
        userService.createIsOneFalseContent(lang.getUser(update));

        return sendPhoto;

    }


//    public void uploadFile(String file_id) throws IOException {
//        URL url = new URL("https://storage.kun.uz/source/thumbnails/_medium/6/t0BMDa8wLWwVfU_-iOnjj95WInhWAR0R_medium.jpg");
////        BufferedReader in = new BufferedReader(new InputStreamReader( url.openStream()));
////        String res = in.readLine();
////        JSONObject jresult = new JSONObject(res);
////        JSONObject path = jresult.getJSONObject("result");
////        String file_path = path.getString("file_path");
//
//
////        C:\Users\Аслом\projects\shop\modules\core\src\com\company\shop\core\file_1.jpg
//
//        File file = new File("C:\\Users\\Аслом\\projects\\shop\\modules\\core\\src\\com\\company\\shop\\core\\file_1.jpg");
//        InputFile inputFile = new InputFile();
//        inputFile.setMedia(file);
//
//        URL downoload = new URL("https://api.telegram.org/file/bot" + "1487031714:AAEz7teOXNw1CmUG9BCUxp225NcWYVZ8p5w" + "/" + file.getAbsolutePath());
//        FileOutputStream fos = new FileOutputStream(file.getAbsoluteFile());
//        System.out.println("Start upload");
//        ReadableByteChannel rbc = Channels.newChannel(downoload.openStream());
//        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//        fos.close();
//        rbc.close();
////        uploadFlag = 0;
//        System.out.println("Uploaded!");
//    }


}
