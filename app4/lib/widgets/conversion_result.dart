import 'package:flutter/material.dart';

class ConversionResult extends StatelessWidget {
  final double result;
  final String fromCurrency;
  final String toCurrency;

  ConversionResult({
    required this.result,
    required this.fromCurrency,
    required this.toCurrency,
  });

  @override
  Widget build(BuildContext context) {
    return Text(
      '$result $toCurrency',
      style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold),
    );
  }
}