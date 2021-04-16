public class TxHandler {

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        // IMPLEMENT THIS
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        // IMPLEMENT THIS
    }ArrayList<Transaction.Input> txInArr = tx.getInputs();
        ArrayList<Transaction.Output> txOutArr = tx.getOutputs();
        ArrayList<UTXO> utxoUsed = new ArrayList<UTXO>();
        double sumIn = 0;
        double sumOut = 0;
        int index = 0;
        // Check inputs of tx
        for (Transaction.Input txIn : txInArr)
        {
            // make a UTXO from tx and use contains() to find if in UTXOPool
            UTXO testUTXO = new UTXO(txIn.prevTxHash, txIn.outputIndex);
            if (currentPool.contains(testUTXO) == false)
                return false;
            for (UTXO ut : utxoUsed)
            {
                if (testUTXO.equals(ut))
                    return false;
            }
            utxoUsed.add(testUTXO);
            // Get the txOuput from the pool
            Transaction.Output checkOut = currentPool.getTxOutput(testUTXO);
            // Public key, Message, Signature
            if (Crypto.verifySignature(checkOut.address, tx.getRawDataToSign(index), txIn.signature) == false)
                return false;
            // Sum tx inputs
            sumIn += checkOut.value;
            index++;
        }
        
        // Check outputs of tx
        for (Transaction.Output txOut : txOutArr)
        {
            if (txOut.value < 0)
                return false;
            sumOut += txOut.value;
        }
        
        if (sumIn < sumOut)
            return false;

        return true;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        // IMPLEMENT THIS
    }

}
