package com.jfmargar.ejemplorecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    //Declaramos las variables para los elementos
    RecyclerView rvBooks;
    Button btnAdd;
    Button btnDelete;
    Button btnMove;
    Button btnSort;

    //Creo la lista de libros vacía para que sea accesible desde cualquier
    //punto del activity
    ArrayList<Book> books = new ArrayList<>();
    BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos los elementos
        rvBooks = findViewById(R.id.rvBooks);
        btnAdd = findViewById(R.id.btnAdd);
        btnDelete = findViewById(R.id.btnDelete);
        btnMove = findViewById(R.id.btnMove);
        btnSort = findViewById(R.id.btnSort);


        //Creo los libros
        final Book book = new Book();
        book.setTitle("Harry Potter y la piedra filosofal");
        book.setSubtitle("Primer libro de la saga de Harry Potter");
        book.setUrlCover("https://images-na.ssl-images-amazon.com/images/I/51PZ5Stxr5L._SX313_BO1,204,203,200_.jpg");

        Book book1 = new Book();
        book1.setTitle("Harry Potter y la cámara secreta");
        book1.setSubtitle("Segundo libro de la saga de Harry Potter");
        book1.setUrlCover("https://images-na.ssl-images-amazon.com/images/I/91%2BBwNDpbSL.jpg");

        //Lo añado a la lista
        books.add(book);
        books.add(book1);

        //Creo el adapter
        adapter = new BookAdapter(MainActivity.this,
                books, R.layout.book_item);

        //Le digo al recyclerview que use el adapter que acabo de crear
        rvBooks.setAdapter(adapter);

        //Por último creo el layoutmanager
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(
                        MainActivity.this,
                        LinearLayoutManager.VERTICAL,
                        false
                );

        rvBooks.setLayoutManager(layoutManager);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO ----
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Lo borramos de la lista
                books.remove(0);
                //luego le decimos al adapter que se ha borrado.
                adapter.notifyItemRemoved(0);
            }
        });

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Cojo el segundo elemento
                Book book2 = books.get(1);
                books.remove(1);
                books.add(0, book2);
                adapter.notifyItemMoved(1, 0);
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ordena la lista books alfabeticamente
                Collections.sort(books, new Comparator<Book>() {
                    @Override
                    public int compare(Book book1, Book book2) {
                        return book1.getTitle().compareToIgnoreCase(book2.getTitle());
                    }
                });

                //Fuera del bloque, la lista ya está ordenada.
                adapter.notifyDataSetChanged();
            }
        });


    }
}
