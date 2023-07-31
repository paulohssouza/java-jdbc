package br.com.aluraoracle.t5one.view;

import br.com.aluraoracle.t5one.controller.CategoryController;
import br.com.aluraoracle.t5one.controller.ProductController;
import br.com.aluraoracle.t5one.model.Category;
import br.com.aluraoracle.t5one.model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;
import java.util.List;

public class ProductCategoryFrame extends JFrame{
    @Serial
    private static final long serialVersionUID = 1L;
    private JLabel labelName, labelDescription, labelCategory;
    private JTextField textName, textDescription;
    private JComboBox<Category> categoryJComboBox;
    private JButton saveButton, editButton, wipeButton, deleteButton;
    private JTable jTable;
    private DefaultTableModel tableModel;
    private ProductController productController;
    private CategoryController categoryController;

    public ProductCategoryFrame() {
        super("Produtos");
        Container container = getContentPane();
        setLayout(null);

        this.categoryController = new CategoryController();
        this.productController = new ProductController();

        labelName = new JLabel("Nome do produto");
        labelDescription = new JLabel("Descrição do produto");
        labelCategory = new JLabel("Categoria do produto");

        labelName.setBounds(10, 10, 240, 15);
        labelName.setForeground(Color.BLACK);

        labelDescription.setBounds(10, 50, 240, 15);
        labelDescription.setForeground(Color.BLACK);

        labelCategory.setBounds(10, 90, 240, 15);
        labelCategory.setForeground(Color.BLACK);

        container.add(labelName);
        container.add(labelDescription);
        container.add(labelCategory);

        textName = new JTextField();
        textName.setBounds(10, 25, 265, 20);

        textDescription = new JTextField();
        textDescription.setBounds(10, 65, 265, 20);

        categoryJComboBox = new JComboBox<>();
        categoryJComboBox.addItem(new Category(0, "Selecione"));
        List<Category> categoryList = this.listCategory();
        categoryList.forEach(category -> categoryJComboBox.addItem(category));
        categoryJComboBox.setBounds(10, 105, 265, 20);

        container.add(textName);
        container.add(textDescription);
        container.add(categoryJComboBox);

        saveButton = new JButton("Salvar");
        saveButton.setBounds(10, 145, 80, 20);

        wipeButton = new JButton("Limpar");
        wipeButton.setBounds(100, 145, 80, 20);

        container.add(saveButton);
        container.add(wipeButton);

        jTable = new JTable();
        tableModel = (DefaultTableModel) jTable.getModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Nome");
        tableModel.addColumn("Descrição");

        fillTable();

        jTable.setBounds(10, 185, 760, 300);
        container.add(jTable);

        deleteButton = new JButton("Excluir");
        deleteButton.setBounds(10, 500, 80, 20);

        editButton = new JButton("Alterar");
        editButton.setBounds(100, 500, 80, 20);

        container.add(deleteButton);
        container.add(editButton);

        setSize(800, 600);
        setVisible(true);
        setLocationRelativeTo(null);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                save();
                clearTable();
                fillTable();
            }
        });

        wipeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                resetFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                delete();
                clearTable();
                fillTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                update();
                clearTable();
                fillTable();
            }
        });
    }

    private List<Category> listCategory() {
        return this.categoryController.list();
    }

    private List<Product> listProduct() {
        return this.productController.list();
    }

    private void fillTable() {
        List<Product> productList = listProduct();
        productList.forEach(product -> tableModel.addRow(new Object[] {
                product.getId(), product.getName(), product.getDescription()}));
    }

    private void clearTable() {
        tableModel.getDataVector().clear();
    }

    private void save() {
        if(validateFields()) {
            this.productController.save(this.createProduct());
            JOptionPane.showMessageDialog(this,
                    "Salvo com sucesso!");
            this.resetFields();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Você deve informar todos os campos.");
        }
    }

    private void delete() {
        Object objectSelected = (Object) tableModel.getValueAt(
                jTable.getSelectedRow(), jTable.getSelectedColumn()
        );
        if(objectSelected instanceof Integer id) {
            this.productController.delete(id);
            tableModel.removeRow(jTable.getSelectedRow());
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Por favor selecione um item da tabela.");
        }
    }

    public void update() {
        Object objectSelected = (Object) tableModel.getValueAt(
                jTable.getSelectedRow(), jTable.getSelectedColumn()
        );
        if(objectSelected instanceof Integer id) {
            String name = (String) tableModel.getValueAt(jTable.getSelectedRow(), 1);
            String description = (String) tableModel.getValueAt(jTable.getSelectedRow(), 2);
            this.productController.update(name, description, id);
            JOptionPane.showMessageDialog(this, "Produto alterado com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto para alterar.");
        }
    }

    private boolean validateFields() {
        return !this.textName.getText().isEmpty() &&
                !this.textDescription.getText().isEmpty() &&
                !(categoryJComboBox.getSelectedIndex() == 0);
    }

    private void resetFields() {
        this.textName.setText("");
        this.textDescription.setText("");
        this.categoryJComboBox.setSelectedIndex(0);
    }

    private Product createProduct() {
        Category category = (Category) categoryJComboBox.getSelectedItem();
        return new Product(this.textName.getText(),
                this.textDescription.getText(), category != null ? category.getId() : null);
    }
}
