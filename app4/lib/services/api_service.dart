import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static Future<double?> convertCurrency(
    String from,
    String to,
    double amount,
  ) async {
    final url =
        'https://v6.exchangerate-api.com/v6/40788923935028c22ea607a8/latest/$from';

    final response = await http.get(Uri.parse(url));

    if (response.statusCode == 200) {
      final data = json.decode(response.body);

      final rate = data['conversion_rates'][to];
      return rate * amount;
    } else {
      return null;
    }
  }
}
