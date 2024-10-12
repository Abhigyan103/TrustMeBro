import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class DecimalVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val inputText = text.text

        // Parse the input text to a double, defaulting to 0 if invalid
        val parsed = inputText.toDoubleOrNull() ?: 0.0

        // Use NumberFormat to format the number with commas and 2 decimal places
        val formatter = NumberFormat.getNumberInstance(Locale("en", "IN"))
        formatter.minimumFractionDigits = 2
        formatter.maximumFractionDigits = 2
        val formattedText = formatter.format(parsed)

        // Create the offset mapping to keep the cursor in sync
        val offsetMapping = CurrencyOffsetMapping(inputText,formattedText)

        // Return the transformed text with the correct offset mapping
        return TransformedText(AnnotatedString(formattedText), offsetMapping)
    }
}

class CurrencyOffsetMapping(private var inputText: String, private var formattedText: String) : OffsetMapping {
    private val indexes = findDigitIndexes(inputText,formattedText)
    private fun findDigitIndexes(firstString: String, secondString: String): List<Int> {
        val digitIndexes = mutableListOf<Int>()
        var currentIndex = 0
        for (digit in firstString) {
            // Find the index of the digit in the second string
            val index = secondString.indexOf(digit, currentIndex)
            if (index != -1) {
                digitIndexes.add(index)
                currentIndex = index + 1
            } else {
                // If the digit is not found, return an empty list
                return emptyList()
            }
        }
        return digitIndexes
    }
    override fun originalToTransformed(offset: Int): Int {
        if (inputText=="")return 0
        if (offset >= inputText.length) {
            return indexes.last() + 1
        }
        return indexes[offset]
    }

    override fun transformedToOriginal(offset: Int): Int {
        return indexes.indexOfFirst { it >= offset }.takeIf { it != -1 } ?: inputText.length
    }

}