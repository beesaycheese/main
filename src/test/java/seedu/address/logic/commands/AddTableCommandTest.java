package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.beans.property.ReadOnlyProperty;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.Mode;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyRestOrRant;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.RestOrRant;
import seedu.address.model.menu.MenuItem;
import seedu.address.model.order.OrderItem;
import seedu.address.model.statistics.Bill;
import seedu.address.model.table.Table;
import seedu.address.model.table.TableNumber;
import seedu.address.model.table.TableStatus;
import seedu.address.testutil.TableBuilder;

public class AddTableCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullTable_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddTableCommand(null);
    }

    @Test
    public void execute_tableAcceptedByModel_addSuccessful() {
        ModelStubAcceptingTableAdded modelStub = new ModelStubAcceptingTableAdded();
        Table validTable = new TableBuilder().build();
        List<TableStatus> tableStatusList = new ArrayList<>();
        tableStatusList.add(validTable.getTableStatus());

        CommandResult commandResult = new AddTableCommand(tableStatusList).execute(
                Mode.RESTAURANT_MODE, modelStub, commandHistory);

        assertEquals(String.format(AddTableCommand.MESSAGE_SUCCESS, validTable), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTable), modelStub.tableAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateOrderItem_throwsCommandException() throws Exception {
        List<TableStatus> tableStatusList = new ArrayList<>();
        tableStatusList.add(new TableStatus("0/4"));
        Table validTable = new TableBuilder().build();
        AddTableCommand addCommand = new AddTableCommand(tableStatusList);
        ModelStub modelStub = new ModelStubWithTable(validTable);

        thrown.expect(CommandException.class);
        thrown.expectMessage(AddToOrderCommand.MESSAGE_DUPLICATE_ORDER_ITEM);
        addCommand.execute(Mode.TABLE_MODE, modelStub, commandHistory);
    }

    @Test
    public void equals() {
        Table table1 = new TableBuilder().withTableStatus(new TableStatus("0/4").toString()).build();
        Table table2 = new TableBuilder().withTableStatus(new TableStatus("0/8").toString()).build();
        List<TableStatus> tableStatusList1 = new ArrayList<>();
        tableStatusList1.add(table1.getTableStatus());
        AddTableCommand addTable1Command = new AddTableCommand(tableStatusList1);
        List<TableStatus> tableStatusList2 = new ArrayList<>();
        tableStatusList2.add(table2.getTableStatus());
        AddTableCommand addTable2Command = new AddTableCommand(tableStatusList2);

        // same object -> returns true
        assertTrue(addTable1Command.equals(addTable1Command));

        // same values -> returns true
        AddTableCommand addTable1CommandCopy = new AddTableCommand(tableStatusList1);
        assertTrue(addTable1Command.equals(addTable1CommandCopy));

        // different types -> returns false
        assertFalse(addTable1Command.equals(1));

        // null -> returns false
        assertFalse(addTable1Command.equals(null));

        // different person -> returns false
        assertFalse(addTable1Command.equals(addTable2Command));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getOrdersFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrdersFilePath(Path restOrRantFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getMenuFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMenuFilePath(Path menuFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTablesFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTablesFilePath(Path tablesFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStatisticsFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStatisticsFilePath(Path statisticsFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyRestOrRant getRestOrRant() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRestOrRant(ReadOnlyRestOrRant restOrRant) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMode() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTable(Table table) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTable(Table table) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTable(Table table) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public TableNumber addTable(TableStatus tableStatus) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTable(Table target, Table editedTable) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Table> getFilteredTableList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTableList(Predicate<Table> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Table> selectedTableProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Table getSelectedTable() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedTable(Table table) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateTables() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasOrderItem(OrderItem orderItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasMenuItem(MenuItem menuItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteOrderItem(OrderItem target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteMenuItem(MenuItem menuItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addOrderItem(OrderItem orderItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addMenuItem(MenuItem menuItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addBill(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setOrderItem(OrderItem target, OrderItem editedOrderItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setMenuItem(MenuItem target, MenuItem editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setBill(Bill target, Bill editedItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<OrderItem> getFilteredOrderItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<MenuItem> getFilteredMenuItemList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bill> getFilteredBillList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredOrderItemList(Predicate<OrderItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredMenuItemList(Predicate<MenuItem> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredBillList(Predicate<Bill> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<OrderItem> selectedOrderItemProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<MenuItem> selectedMenuItemProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyProperty<Bill> selectedBillProperty() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public OrderItem getSelectedOrderItem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedOrderItem(OrderItem orderItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public MenuItem getSelectedMenuItem() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedMenuItem(MenuItem menuItem) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Bill getSelectedBill() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setSelectedBill(Bill bill) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateMenu() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateOrders() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Bill> getBillList() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTable extends ModelStub {
        private final Table table;

        ModelStubWithTable(Table table) {
            requireNonNull(table);
            this.table = table;
        }

        @Override
        public boolean hasTable(Table table) {
            requireNonNull(table);
            return this.table.isSameTable(table);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingTableAdded extends ModelStub {
        final ArrayList<Table> tableAdded = new ArrayList<>();

        @Override
        public boolean hasTable(Table table) {
            requireNonNull(table);
            return tableAdded.stream().anyMatch(table::isSameTable);
        }

        @Override
        public void addTable(Table table) {
            requireNonNull(table);
            tableAdded.add(table);
        }

        @Override
        public void updateMode() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlyRestOrRant getRestOrRant() {
            return new RestOrRant();
        }
    }

}
