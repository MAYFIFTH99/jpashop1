package jpa.jpashop1.service;

import jpa.jpashop1.controller.BookForm;
import jpa.jpashop1.domain.item.Book;
import jpa.jpashop1.domain.item.Item;
import jpa.jpashop1.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Item findById(Long id) {
        return itemRepository.findById(id);
    }

    @Transactional
    public void updateItem(Long itemId, BookForm form) {
        Book book = (Book)itemRepository.findById(itemId);
        book.setName(form.getName());
        book.setPrice(form.getPrice());
        book.setStockQuantity(form.getStockQuantity());
        book.setAuthor(form.getAuthor());
        book.setIsbn(form.getIsbn());
        // 영속성 컨텍스트에 존재하는 엔티티를 추출한 후 , 그 값을 수정함으로써
        // 변경 감지를 이용해야 하지, 엔티티를 어설프게 수정하는 것은 매우 위험하다.
    }


}
