import 'package:cloud_firestore/cloud_firestore.dart';

class FirestoreService {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;

  Future<void> saveConversion(String fromCurrency, String toCurrency, double amount, double result) async {
    await _firestore.collection('conversion_history').add({
      'from_currency': fromCurrency,
      'to_currency': toCurrency,
      'amount': amount,
      'result': result,
      'timestamp': FieldValue.serverTimestamp(),
    });
  }

  Stream<QuerySnapshot> getConversionHistory() {
    return _firestore.collection('conversion_history').orderBy('timestamp', descending: true).snapshots();
  }
}
