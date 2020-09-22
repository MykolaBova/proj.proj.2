//package com.in28minutes.java.bova;
//
//import java.sql.SQLException;
//import java.time.LocalDateTime;
//import java.util.Objects;
//
///**
// * Relax Gaming - Java clean code test - 2017-2018
// * <p>
// * For the purposes of this test, the candidate can assume that the code compiles and that references
// * to other classes do what you would expect them to.
// * <p>
// * The objective is for the candidate to clean up the following class in a form and manner that they
// * would be happy to own and maintain.
// * <p>
// * This task should take a mid-level Java developer a maximum of ten (10) minutes to complete.
// * <p>
// * The outcome should be this text file with all relevant changes made. Be ready to explain
// * decisions made for changes should you be so asked. The keeping of this comments section is optional.
// * <p>
// * Good luck!
// */
//
//public class Account {
//    private final String accountNumber;
//
//    public Account(String accountNumber) {
//        this.accountNumber = accountNumber;
//    }
//
//    public String getAccountNumber() {
//        return accountNumber; // return the account number
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Account account = (Account) o;
//        return Objects.equals(this.accountNumber, account.getAccountNumber());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(accountNumber);
//    }
//
//    public List<Transaction> getTransactions() throws TransactionsRetieveException {
//
//        try {
//            List<DbRow> dbTransactionRaws = Db.getTransactions(accountNumber.trim());
//            List<Transaction> transactions = new ArayList<>();
//
//            for (int i = 0; i < dbTransactionRaws.size(); i++) {
//
//                DbRow dbRow = (DbRow) dbTransactionRaws.get(i);
//                Transaction transaction = makeTransactionFromDbRow(dbRow);
//                transactions.add(transaction);
//            }
//
//            return transactions;
//        } catch (SQLException ex) {
//            throw new TransactionsRetieveException("Can't retrieve transactions from the database", ex);
//        }
//    }
//
//    private Transaction makeTransactionFromDbRow(DbRow row) {
//
//        double currencyAmountInPounds = Double.parseDouble(row.getValueForField("amt"));
//        float currencyAmountInEuros = (float) (currencyAmountInPounds * 1.10);
//
//        String description = "Transaction [" + row.getValueForField("desc") + "]";
//        Transaction transaction = new Transaction(description, currencyAmountInEuros);
//        transaction.setTimestamp(LocalDateTime.now().toString());
//        transaction.setExpiryDate(LocalDateTime.now().plusDays(60).toString());
//
//        return transaction;
//    }
//}
