// IBookManager.aidl
package com.tzx.aidldemo.aidl;
import com.tzx.aidldemo.aidl.Book;
interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
