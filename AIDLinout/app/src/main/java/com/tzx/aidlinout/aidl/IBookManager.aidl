// IBookManager.aidl
package com.tzx.aidlinout.aidl;
import com.tzx.aidlinout.aidl.Book;
// Declare any non-default types here with import statements

interface IBookManager {
    Book addInBook(in Book book);
    Book addOutBook(out Book book);
    Book addInoutBook(inout Book book);
    List<Book> getBookList();
}
