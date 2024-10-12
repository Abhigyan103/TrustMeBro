package com.abhigyan.trustmebro.upi

import android.content.Intent
import android.net.Uri
import com.abhigyan.trustmebro.calculator.types.Transaction
import java.util.UUID

private fun getUPIString(
    payeeAddress: String,
    payeeName: String,
    trxnNote: String,
    payeeAmount: String,
    transactionId: String = UUID.randomUUID().toString(),
    transactionRefId: String = UUID.randomUUID().toString()
): String {
    val UPI = ("upi://pay?pa=$payeeAddress&pn=${payeeName}&tn=$trxnNote&am=$payeeAmount&tid=$transactionId&tr=$transactionRefId&cu=INR")
    return UPI.replace(" ", "+")
}

fun getPaymentIntent(upiId: String, note: String, amount: Double) : Intent{
    val UPI = getUPIString(
        upiId,
        "Aha",
        note,
        amount.toString()
    )
    val intent = Intent()
    intent.setAction(Intent.ACTION_VIEW)
    intent.setData(Uri.parse(UPI))
    println(UPI)
    val chooser = Intent.createChooser(intent, "Select a payment app")
    return chooser
}