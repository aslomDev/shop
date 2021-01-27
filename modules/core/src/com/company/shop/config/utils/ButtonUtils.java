package com.company.shop.config.utils;

import com.company.shop.config.SessionIdConfig;
import com.company.shop.config.LangConfig;
import com.company.shop.service.CategoryService;
import com.company.shop.service.GoodsService;
import com.company.shop.service.ProductService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

@Component
public class ButtonUtils {
    @Inject private InlineButton inlineButton;
    @Inject private GoodsService goodsService;
    @Inject private CategoryService categoryService;
    @Inject private ProductService productService;
    @Inject private LangConfig langConfig;
    @Inject private SessionIdConfig sessionIdConfig;

    public List<List<InlineKeyboardButton>> goodsCollection(){
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        List<InlineKeyboardButton> row = new LinkedList<>();
        if (goodsService.getGoods().isEmpty()){
            if (langConfig.getLang().equals("ru")){
               collection.add(inlineButton.row(
                       inlineButton.button("пока не чего нету", "null")
               ));
                collection.add(inlineButton.row(
                        inlineButton.button("назад", "ru")
                ));
                return collection;
            }else if (langConfig.getLang().equals("uz")){
                collection.add(inlineButton.row(
                        inlineButton.button("hozircha hech nima yo'q", "null")
                ));
                collection.add(inlineButton.row(
                        inlineButton.button("orqaga", "uz")
                ));
                return collection;
            }
            return null;
        }else {
           if (langConfig.getLang().equals("uz")){
               goodsService.getGoods().forEach(i -> {
                   InlineKeyboardButton button = new InlineKeyboardButton();
                   button.setText(i.getGoodsUz());
                   button.setCallbackData("goods#Category" + i.getId());
                   row.add(button);
               });
               collection.add(row);
               collection.add(inlineButton.row(
                       inlineButton.button("orqaga", "start")
               ));
               return collection;
           }else if (langConfig.getLang().equals("ru")){
               goodsService.getGoods().forEach(i -> {
                   InlineKeyboardButton button = new InlineKeyboardButton();
                   button.setText(i.getGoodsRu());
                   button.setCallbackData("goods#Category" + i.getId());
                   sessionIdConfig.setGoodsId(i.getId());
                   System.out.println("id ru "+ i.getId());
//                   System.out.println("id goods "+ sessionIdConfig.getShopId());
                   row.add(button);
               });
               collection.add(row);
               collection.add(inlineButton.row(
                       inlineButton.button("назад", "start")
               ));
               return collection;
           }
           return null;
        }
    }


    public List<List<InlineKeyboardButton>> CategoryCollection(Integer id) {
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        List<InlineKeyboardButton> row = new LinkedList<>();
        if (categoryService.getCategory(id).isEmpty()) {
            if (langConfig.getLang().equals("ru")){
                collection.add(inlineButton.row(
                        inlineButton.button("пока не чего нету", "null")
                ));

                collection.add(inlineButton.row(
                        inlineButton.button("назад", "ru")
                ));
                return collection;
            }else if (langConfig.getLang().equals("uz")){
                collection.add(inlineButton.row(
                        inlineButton.button("hozircha hech nima yo'q", "null")
                ));

                collection.add(inlineButton.row(
                        inlineButton.button("orqaga", "uz")
                ));
                return collection;
            }
            return null;
        } else {
          if (langConfig.getLang().equals("uz")){
              categoryService.getCategory(id).forEach(i -> {
                  InlineKeyboardButton button = new InlineKeyboardButton();
                  button.setText(i.getCategoryUz());
                  button.setCallbackData("category#Product" + i.getId());
                  row.add(button);
              });
              collection.add(row);
              collection.add(inlineButton.row(
                      inlineButton.button("orqaga", "uz")
              ));
              return collection;
          }else if (langConfig.getLang().equals("ru")){
              categoryService.getCategory(id).forEach(i -> {
                  InlineKeyboardButton button = new InlineKeyboardButton();
                  button.setText(i.getCategoryRu());
                  button.setCallbackData("category#Product" + i.getId());
                  row.add(button);
              });
              collection.add(row);
              collection.add(inlineButton.row(
                      inlineButton.button("назад", "ru")
              ));
              return collection;
          }
          return null;
        }
    }

    public List<List<InlineKeyboardButton>> productCollection(Integer id) {
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        List<InlineKeyboardButton> row = new LinkedList<>();
        if (productService.getProduct(id).isEmpty()) {
            if (langConfig.getLang().equals("ru")){
                collection.add(inlineButton.row(
                        inlineButton.button("пока не чего нету", "null")
                ));

                collection.add(inlineButton.row(
                        inlineButton.button("назад", "goods#")
                ));
                return collection;
            }else if (langConfig.getLang().equals("uz")){
                collection.add(inlineButton.row(
                        inlineButton.button("hozircha hech nima yo'q", "null")
                ));

                collection.add(inlineButton.row(
                        inlineButton.button("orqaga", "goods#")
                ));
                return collection;
            }
            return null;
        } else {
            if (langConfig.getLang().equals("uz")){
                productService.getProduct(id).forEach(i -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(i.getDescriptionUz());
                    button.setCallbackData("product#" + i.getId());
                    row.add(button);
                });
                collection.add(row);
                collection.add(inlineButton.row(
                        inlineButton.button("orqaga", "goods#")
                ));
                return collection;
            }else if (langConfig.getLang().equals("ru")){
                productService.getProduct(id).forEach(i -> {
                    InlineKeyboardButton button = new InlineKeyboardButton();
                    button.setText(i.getProductRu());
                    button.setCallbackData("product#" + i.getId());
                    row.add(button);
                });
                collection.add(row);
                collection.add(inlineButton.row(
                        inlineButton.button("назад", "goods#")
                ));
                return collection;
            }
            return null;
        }
    }




}
