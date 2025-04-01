import 'package:flutter/material.dart';
import '../widgets/currency_dropdown.dart';
import '../widgets/conversion_result.dart';
import '../services/api_service.dart';
import '../services/firestore_service.dart';
import '../localization/app_localizations.dart';

class ConverterScreen extends StatefulWidget {
  @override
  _ConverterScreenState createState() => _ConverterScreenState();
}

class _ConverterScreenState extends State<ConverterScreen> {
  String fromCurrency = 'USD';
  String toCurrency = 'EUR';
  double amount = 1.0;
  double? result;

  final FirestoreService _firestoreService = FirestoreService(); // Firestore service instance

  void convertCurrency() async {
    double? conversionResult = await ApiService.convertCurrency(
      fromCurrency,
      toCurrency,
      amount,
    );

    setState(() {
      result = conversionResult;
    });

    if (conversionResult != null) {
      await _firestoreService.saveConversion(fromCurrency, toCurrency, amount, conversionResult);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          AppLocalizations.of(context)?.translate('converter_title') ?? 'Currency Converter',
          style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
        backgroundColor: Colors.blueAccent,
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.blue.shade100, Colors.blue.shade300],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: Padding(
          padding: const EdgeInsets.all(20.0),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              CurrencyDropdown(
                label: AppLocalizations.of(context)?.translate('from currency') ?? 'From Currency',
                value: fromCurrency,
                onChanged: (value) {
                  setState(() {
                    fromCurrency = value!;
                  });
                },
              ),
              SizedBox(height: 20),

              CurrencyDropdown(
                label: AppLocalizations.of(context)?.translate('to currency') ?? 'To Currency',
                value: toCurrency,
                onChanged: (value) {
                  setState(() {
                    toCurrency = value!;
                  });
                },
              ),
              SizedBox(height: 20),

              TextField(
                decoration: InputDecoration(
                  labelText: AppLocalizations.of(context)?.translate('amount') ?? 'Amount',
                  border: OutlineInputBorder(),
                ),
                keyboardType: TextInputType.number,
                onChanged: (value) {
                  setState(() {
                    amount = double.tryParse(value) ?? 1.0;
                  });
                },
              ),
              SizedBox(height: 20),

              ElevatedButton(
                onPressed: convertCurrency,
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                  backgroundColor: Colors.blueAccent,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20),
                  ),
                ),
                child: Text(
                  AppLocalizations.of(context)?.translate('convert') ?? 'Convert',
                  style: TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
              SizedBox(height: 20),

              if (result != null)
                ConversionResult(
                  result: result!,
                  fromCurrency: fromCurrency,
                  toCurrency: toCurrency,
                ),
            ],
          ),
        ),
      ),
    );
  }
}