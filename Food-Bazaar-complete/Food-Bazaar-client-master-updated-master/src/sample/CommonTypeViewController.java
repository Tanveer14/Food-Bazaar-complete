package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.util.converter.DoubleStringConverter;

import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;

public class CommonTypeViewController implements Initializable {

    Socket socket;
    public static String Currentpage=null;
    @FXML ComboBox Unit1,Unit2,Unit3,Unit4,Unit5;
    @FXML Label Label1,Label2,Label3,Label4,Label5,caption;
    @FXML Label TotalPriceValue;
    @FXML Button button1,button2,button3,button4,button5;
    @FXML VBox vbox1,vbox2,vbox3,vbox4,vbox5;
    ObservableList<product> productList= FXCollections.observableArrayList();
    TextFieldTableCell TextFieldTableCell=new TextFieldTableCell();
    TextFieldTableCell TextFieldTableCell2=new TextFieldTableCell();
    @FXML Button previousButton,DeleteItemButton,nextButton, goBackButton, ConfirmButton;
    @FXML TreeView<String> FoodTree;
    @FXML TableView<product> FoodTable;
    @FXML TableColumn<product,String> NameColumn;
    @FXML TableColumn<product,Double> UnitColumn;
    @FXML TableColumn<product,Double> PriceColumn;
    @FXML TableColumn<product,String> TypeColumn;
    @FXML TableColumn<product,String> UnitTypeColumn;
    private Map<String,Double> TableItems=new HashMap<>();
    private Map<String,Integer> SelectedInCart=new HashMap<>();

    public static double totalPrice;
    private int k,count;
    ArrayList<product> temp=new ArrayList<>();
    public static ArrayList<product> TableProductList=new ArrayList<>();



    private static void nullValue()
    {
        Alert alert=new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText("You didn't select any quantity of item.");
        alert.showAndWait();
    }
    public void ConfirmButtonClicked(ActionEvent e) throws IOException {
        Currentpage=caption.getText();
        product demoproduct;
        for(int i=0;i<FoodTable.getItems().size();i++){
            demoproduct=FoodTable.getItems().get(i);
            TableProductList.add(demoproduct);
        }
        if(TableProductList.size()!=0){
            FoodTable.getItems().clear();
            Parent newsceneparent= FXMLLoader.load(getClass().getResource("LogInPage.fxml"));
            Common.ButtonClicked(e,newsceneparent);
        }
        else {
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Cart cannot be empty .");
            alert.showAndWait();
        }

    }

    public void alertmessage(Double d,String s){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Sorry  :(:(\n"+"We only have "+d+ " more items of "+s+" available in our stock");
        alert.showAndWait();
    }

    public void alertSelect(ComboBox s){
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("This item is already selected");
        alert.showAndWait();
        s.getSelectionModel().clearSelection();
    }


    public void getbackButtonClicked(ActionEvent event) throws Exception{

        Parent page= FXMLLoader.load(getClass().getResource("ShopTypesView.fxml"));
        Common.ButtonClicked(event,page);
    }

    public void AddToCartButton1Clicked()
    {
        if(SelectedInCart.get(temp.get(k-count).getName())==1){
            alertSelect(Unit1);
            return;
        }

        if(Unit1.getValue()==null) {
            nullValue();
            return;
        }

        temp.get(k-count).setAvailable_units(TableItems.get(temp.get(k-count).getName()));
        if((TableItems.get(temp.get(k-count).getName())-Double.parseDouble(String.valueOf(Unit1.getValue())))>=0){
            try {
                SelectedInCart.put(temp.get(k-count).getName(),1);
                int getValue=Integer.parseInt(String.valueOf(Unit1.getValue()));
                Common.AddToTable(Label1,caption,TotalPriceValue, Unit1, FoodTable, productList);
                temp.get(k-count).exclude_available_units(getValue);
                TableItems.put(temp.get(k -count).getName(),temp.get(k -count).getAvailable_units());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else alertmessage(temp.get(k-count).getAvailable_units(),temp.get(k-count).getName());
        Unit1.getSelectionModel().clearSelection();

    }
    public void AddToCartButton2Clicked() {
        if(SelectedInCart.get(temp.get(k-count+1).getName())==1){
            alertSelect(Unit2);
            return;
        }

        if(Unit2.getValue()==null){
            nullValue();
            return;
        }

        temp.get(k-count+1).setAvailable_units(TableItems.get(temp.get(k-count+1).getName()));
        if ((TableItems.get(temp.get(k-count+1).getName()) - Double.parseDouble(String.valueOf(Unit2.getValue()))) >= 0) {
            try {
                SelectedInCart.put(temp.get(k-count+1).getName(),1);
                int getValue=Integer.parseInt(String.valueOf(Unit2.getValue()));
                Common.AddToTable(Label2,caption,TotalPriceValue, Unit2, FoodTable, productList);
                temp.get(k -count+ 1).exclude_available_units(getValue);
                TableItems.put(temp.get(k -count+1 ).getName(),temp.get(k -count+ 1).getAvailable_units());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else alertmessage(temp.get(k-count+1).getAvailable_units(),temp.get(k-count+1).getName());
        Unit2.getSelectionModel().clearSelection();
    }
    public void AddToCartButton3Clicked()
    {
        if(SelectedInCart.get(temp.get(k-count+2).getName())==1){
            alertSelect(Unit3);
            return;
        }

        if(Unit3.getValue()==null) {
            nullValue();
            return;
        }

        temp.get(k-count+2).setAvailable_units(TableItems.get(temp.get(k-count+2).getName()));
        if((TableItems.get(temp.get(k-count+2).getName())-Double.parseDouble(String.valueOf(Unit3.getValue())))>=0){
            try {
                SelectedInCart.put(temp.get(k-count+2).getName(),1);
                int getValue=Integer.parseInt(String.valueOf(Unit3.getValue()));
                Common.AddToTable(Label3,caption,TotalPriceValue, Unit3, FoodTable, productList);
                temp.get(k -count+ 2).exclude_available_units(getValue);
                TableItems.put(temp.get(k -count+ 2).getName(),temp.get(k -count+2 ).getAvailable_units());
            }catch (Exception e)
            {
                System.out.println(e);
            }
        }
        else alertmessage(temp.get(k-count+2).getAvailable_units(),temp.get(k-count+2).getName());
        Unit3.getSelectionModel().clearSelection();
    }

    public void AddToCartButton4Clicked() {
        if(SelectedInCart.get(temp.get(k-count+3).getName())==1){
            alertSelect(Unit4);
            return;
        }

        if(Unit4.getValue()==null) {
            nullValue();
            return;
        }

        temp.get(k-count+3).setAvailable_units(TableItems.get(temp.get(k-count+3).getName()));
        if ((TableItems.get(temp.get(k-count+3).getName())- Double.parseDouble(String.valueOf(Unit4.getValue()))) >= 0) {
            try {
                SelectedInCart.put(temp.get(k-count+3).getName(),1);
                int getValue=Integer.parseInt(String.valueOf(Unit4.getValue()));
                Common.AddToTable(Label4,caption,TotalPriceValue, Unit4, FoodTable, productList);
                temp.get(k -count+ 3).exclude_available_units(getValue);
                TableItems.put(temp.get(k -count+ 3).getName(),temp.get(k -count+ 3).getAvailable_units());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else alertmessage(temp.get(k-count+3).getAvailable_units(),temp.get(k-count+3).getName());
        Unit4.getSelectionModel().clearSelection();
    }
    public void AddToCartButton5Clicked() {
        if(SelectedInCart.get(temp.get(k-count+4).getName())==1){
            alertSelect(Unit5);
            return;
        }

        if(Unit5.getValue()==null) {
            nullValue();
            return;
        }

        temp.get(k-count+4).setAvailable_units(TableItems.get(temp.get(k-count+4).getName()));
        if ((TableItems.get(temp.get(k-count+4).getName())- Double.parseDouble(String.valueOf(Unit5.getValue()))) >= 0) {
            try {
                SelectedInCart.put(temp.get(k-count+4).getName(),1);
                int getValue=Integer.parseInt(String.valueOf(Unit5.getValue()));
                Common.AddToTable(Label5,caption,TotalPriceValue, Unit5, FoodTable, productList);
                temp.get(k -count+ 4 ).exclude_available_units(getValue);
                TableItems.put(temp.get(k -count+  4).getName(),temp.get(k -count+ 4 ).getAvailable_units());
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        else alertmessage(temp.get(k-count+4).getAvailable_units(),temp.get(k-count+4).getName());
        Unit5.getSelectionModel().clearSelection();
    }

    @FXML
    public void TableUnitEdit(TableColumn.CellEditEvent<product,Double> e){

        productList.addAll(FoodTable.getItems());
        double oldUnit=e.getOldValue();
        double newUnit=e.getNewValue();

        ObservableList<product> productSelected;
        productSelected = FoodTable.getSelectionModel().getSelectedItems();


        if(newUnit<=0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("Unit of an item cannot be zero or negative. If you intend to remove the selected item, " +
                    "press the Delete Item button .");
            alert.showAndWait();
            newUnit=1;
        }
        else if(TableItems.get(productSelected.get(0).getName())+oldUnit-newUnit<0){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("The amount of unit selected for this item is not available in our stock.");
            alert.showAndWait();
            newUnit=oldUnit;
        }
        else if(newUnit>5){
            Alert alert=new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setContentText("You cannot order more than 5 Unit of an item at a time .");
            alert.showAndWait();
            newUnit=5;
        }

        Double amount=TableItems.get(productSelected.get(0).getName())+oldUnit-newUnit;
        TableItems.put(productSelected.get(0).getName(), amount);

        double oldPrice=FoodTable.getSelectionModel().getSelectedItem().getPrice();
        double newPrice=oldPrice/oldUnit*newUnit;
        FoodTable.getItems().get(e.getTablePosition().getRow()).setUnit(newUnit);
        //FoodTable.getItems().get(FoodTable.getSelectionModel().getFocusedIndex()).setUnit(newUnit);
        int index=FoodTable.getSelectionModel().getSelectedIndex();
        /*for (product i:productList
             ) {
            System.out.println(i);
        }*/
        productList.get(index).setPrice(newPrice);
        FoodTable.getItems().clear();
        FoodTable.getItems().addAll(productList);

        totalPrice=0;
        for (product p:productList
        ) {
            totalPrice+=p.getPrice();

        }
        TotalPriceValue.setText(String.valueOf(totalPrice));
        productList.clear();

    }


    public void DeleteButtonClicked()
    {
       try{
           ObservableList<product> allProducts,productSelected;
           allProducts = FoodTable.getItems();
           productSelected = FoodTable.getSelectionModel().getSelectedItems();
           Double amount=TableItems.get(productSelected.get(0).getName())+productSelected.get(0).getUnit();
           TableItems.put(productSelected.get(0).getName(),amount);
           SelectedInCart.put(productSelected.get(0).getName(),0);
           if(productSelected.toString().equals("[]")) return;
           Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
           alert.setHeaderText(null);
           alert.setContentText("Are you sure you want to remove this item from your cart?");
           Optional<ButtonType> action=alert.showAndWait();
           if(action.get()==ButtonType.OK) {
               productSelected.forEach(allProducts::remove);
               totalPrice = 0;
               for (product p : FoodTable.getItems()
               ) {

                   totalPrice += p.getPrice();
               }
               TotalPriceValue.setText(String.valueOf(totalPrice));
           }
       }catch (Exception ex){
           Alert alert=new Alert(Alert.AlertType.WARNING);
           alert.setHeaderText(null);
           alert.setContentText("No item is selected !");
           alert.showAndWait();
       }
    }

    public void nextButtonClicked() throws Exception{
        previousButton.setVisible(true);
        int l=setLabelText();
        if(l==0) nextButton.setVisible(false);
    }

    public void previousButtonClicked() throws Exception{
        k=k-count-5;
        nextButton.setVisible(true);
        if(k==0) previousButton.setVisible(false);
        int l=setLabelText();

    }

    public void TreeViewClicked()
    {

        try {
            String strTemp= FoodTree.getSelectionModel().getSelectedItem().getValue();
            nextButton.setVisible(false);
            previousButton.setVisible(false);
            k=0;
            if(!strTemp.equals("Food Items"))setScene(strTemp);
        }catch (NullPointerException ne){

        }
    }



    private int setLabelText(){
        vbox1.setVisible(false);
        vbox2.setVisible(false);
        vbox3.setVisible(false);
        vbox4.setVisible(false);
        vbox5.setVisible(false);
        Label1.setText("");
        Label2.setText("");
        Label3.setText("");
        Label4.setText("");
        Label5.setText("");
        count=0;
        if(k==temp.size()) return 0;
        Label1.setText(temp.get(k).getName()+"\n"+temp.get(k).getPrice()+" tk\nper "+temp.get(k).getUnit_type());
        vbox1.setVisible(true);
        k++;
        count++;
        if(k==temp.size()) return 0;
        Label2.setText(temp.get(k).getName()+"\n"+temp.get(k).getPrice()+" tk\nper "+temp.get(k).getUnit_type());
        vbox2.setVisible(true);
        k++;
        count++;
        if(k==temp.size()) return 0;
        Label3.setText(temp.get(k).getName()+"\n"+temp.get(k).getPrice()+" tk\nper "+temp.get(k).getUnit_type());
        vbox3.setVisible(true);
        k++;
        count++;
        if(k==temp.size()) return 0;
        Label4.setText(temp.get(k).getName()+"\n"+temp.get(k).getPrice()+" tk\nper "+temp.get(k).getUnit_type());
        vbox4.setVisible(true);
        k++;
        count++;
        if(k==temp.size()) return 0;
        Label5.setText(temp.get(k).getName()+"\n"+temp.get(k).getPrice()+" tk\nper "+temp.get(k).getUnit_type());
        vbox5.setVisible(true);
        k++;
        count++;
        if(k==temp.size()) return 0;
        return 1;
    }

    public void setScene(String strtemp)
    {

        try{
            socket=new Socket("localhost",4444);
            ObjectOutputStream outtoServer=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream oi=new ObjectInputStream(socket.getInputStream());
            outtoServer.writeObject(strtemp);
            temp= (ArrayList<product>) oi.readObject();
            for(product i:temp){
                if(!(TableItems.containsKey(i.getName())))
                    TableItems.put(i.getName(),i.getAvailable_units());
                if(!(SelectedInCart.containsKey(i.getName())))
                    SelectedInCart.put(i.getName(),0);
            }
            socket.close();
        }catch (Exception ex){
            System.out.println(ex);
        }
        //File file=new File(strtemp.toLowerCase()+".txt");
        //temp=Common.ownerFileInput(file);
        caption.setText(strtemp);
        k=0;
        int l=setLabelText();
        if(l==1){
            nextButton.setVisible(true);
        }
        try {
            setTreeview(FoodTree);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setTreeview(TreeView<String> treeview) throws IOException, ClassNotFoundException {

        TreeItem<String> foods=new TreeItem<>("Food Items");
        treeview.setRoot(foods);
        ArrayList<TreeItem<String>>treeItems=new ArrayList<>();

       /*FileInputStream fin=new FileInputStream("type list");
        ObjectInputStream oin=new ObjectInputStream(fin);*/


        ArrayList<String> types=new ArrayList<>();
        try{
            socket=new Socket("localhost",4444);
            ObjectOutputStream outtoServer=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream oi=new ObjectInputStream(socket.getInputStream());
            String s="type list";
            outtoServer.writeObject(s);
            types = (ArrayList<String>) oi.readObject();


        }catch(Exception ex){
            System.out.println(ex);
        }

        int i=0;
        while (true){
            String itemtext=types.get(i);
            treeItems.add(new TreeItem<>(itemtext));
            i++;
            if(i>=types.size())break;

        }
        foods.getChildren().addAll(treeItems);
        foods.setExpanded(true);

        // tree=treeview;


    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        UnitColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        UnitTypeColumn.setCellValueFactory(new PropertyValueFactory<>("unit_type"));
        TableViewControl.InitTable(FoodTable,productList);
        FoodTable.setEditable(true);
        UnitColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        PriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        PriceColumn.setEditable(false);
        totalPrice=0;
        FoodTable.getItems().clear();
        TableProductList.clear();
        productList.clear();


        ArrayList<String> choice=Common.choices();
        Unit1.getItems().addAll(choice);
        Unit2.getItems().addAll(choice);
        Unit3.getItems().addAll(choice);
        Unit4.getItems().addAll(choice);
        Unit5.getItems().addAll(choice);

        String strtemp;
        try{
            strtemp=ShopTypesViewController.SelectedType;
        }catch(Exception ex){
            strtemp=LogInPage.gobackPage;
        }
        setScene(strtemp);


        try {
            setTreeview(FoodTree);
        }catch(Exception e){
            System.out.println(e);
        }

        button1.setStyle("-fx-background-color: #232020;");
        button2.setStyle("-fx-background-color: #232020;");
        button3.setStyle("-fx-background-color: #232020;");
        button4.setStyle("-fx-background-color: #232020;");
        button5.setStyle("-fx-background-color: #232020;");
        previousButton.setStyle("-fx-background-color: #232020;");
        nextButton.setStyle("-fx-background-color: #232020;");
        goBackButton.setStyle("-fx-background-color: #232020;");
        ConfirmButton.setStyle("-fx-background-color: #232020;");
        DeleteItemButton.setStyle("-fx-background-color: #232020;");
        FoodTable.setStyle("-fx-border-color: #232020;");
        Unit1.setStyle("-fx-background-color: #fce28c;");
        Unit2.setStyle("-fx-background-color: #fce28c;");
        Unit3.setStyle("-fx-background-color: #fce28c;");
        Unit4.setStyle("-fx-background-color: #fce28c;");
        Unit5.setStyle("-fx-background-color: #fce28c;");
    }
}
