package com.company.shop.config.utils;

import com.company.shop.service.CategoryService;
import com.company.shop.service.GoodsService;
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

    /// methods

    public List<List<InlineKeyboardButton>> goodsCollection(String lang, String userId){
        List<InlineKeyboardButton> row = new LinkedList<>();
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        if (goodsService.getGoods().isEmpty()){
            if (lang.equals("ru")){
               collection.add(inlineButton.row(
                       inlineButton.button("пока не чего нету", "null")
               ));
                collection.add(inlineButton.row(
                        inlineButton.button("назад", "ru")
                ));
                return collection;
            }else  if (lang.equals("uz")){
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
            if (lang.equals("uz")){
               goodsService.getGoods().forEach(i -> {
                   InlineKeyboardButton button = new InlineKeyboardButton();
                   button.setText(i.getGoodsUz());
                   button.setCallbackData("goods#Category" + i.getId());
//                   userService.CreateGoods(userId, i.getId());
                   row.add(button);
               });
               collection.add(row);
               collection.add(inlineButton.row(
                       inlineButton.button("orqaga", "start")
               ));
               return collection;
           }else  if (lang.equals("ru")){
               goodsService.getGoods().forEach(i -> {
                   InlineKeyboardButton button = new InlineKeyboardButton();
                   button.setText(i.getGoodsRu());
                   button.setCallbackData("goods#Category" + i.getId());
                   System.out.println("id ru "+ i.getId());
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


    public List<List<InlineKeyboardButton>> categoryCollection(Integer id, String userId, String lang) {
        List<List<InlineKeyboardButton>> collection = new LinkedList<>();
        List<InlineKeyboardButton> row = new LinkedList<>();
        if (categoryService.getCategory(id).isEmpty()) {
            if (lang.equals("ru")){
                collection.add(inlineButton.row(
                        inlineButton.button("пока не чего нету", "null")
                ));

                collection.add(inlineButton.row(
                        inlineButton.button("назад", "ru")
                ));
                return collection;
            }else  if (lang.equals("uz")){
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
            if (lang.equals("uz")){
              categoryService.getCategory(id).forEach(i -> {
                  InlineKeyboardButton button = new InlineKeyboardButton();
                  List<InlineKeyboardButton> row2 = new LinkedList<>();
                  button.setText(i.getCategoryUz());
                  button.setCallbackData("category#Product" + i.getId());
                  row2.add(button);
                  collection.add(row2);
              });
              collection.add(inlineButton.row(
                      inlineButton.button("orqaga", "uz")
              ));
              return collection;
          }else  if (lang.equals("ru")){
              categoryService.getCategory(id).forEach(i -> {
                  InlineKeyboardButton button = new InlineKeyboardButton();
                  List<InlineKeyboardButton> row2 = new LinkedList<>();
                  button.setText(i.getCategoryRu());
                  button.setCallbackData("category#Product" + i.getId());
                  row2.add(button);
                  collection.add(row2);
              });
//              collection.add(row);
              collection.add(inlineButton.row(
                      inlineButton.button("назад", "ru")
              ));
              return collection;
          }
          return null;
        }
    }

        public List<List<InlineKeyboardButton>> contentIsNull(String lang) {
            List<List<InlineKeyboardButton>> collection = new LinkedList<>();
            List<InlineKeyboardButton> row = new LinkedList<>();
            if (lang.equals("uz")){
                collection.add(inlineButton.row(inlineButton.button("hech nima topilmadi", "null")));
                collection.add(inlineButton.row(inlineButton.button("orqaga", "goods#")));
                return collection;
            }else if (lang.equals("ru")){
                collection.add(inlineButton.row(inlineButton.button("Ничего не найдено", "null")));
                collection.add(inlineButton.row(inlineButton.button("назад", "goods#")));
                return collection;
            }
            return null;

        }



}
