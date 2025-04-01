import 'package:flutter/material.dart';
import 'converter_screen.dart';
import '../localization/app_localizations.dart';

class HomeScreen extends StatelessWidget {
  final Function(Locale) onLanguageChange;

  HomeScreen({required this.onLanguageChange});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          AppLocalizations.of(context)?.translate('home_title') ?? 'Currency Converter',
          style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
        backgroundColor: const Color.fromARGB(255, 255, 40, 40),
      ),
      body: Container(
        decoration: BoxDecoration(
          gradient: LinearGradient(
            colors: [Colors.blue.shade100, Colors.blue.shade300],
            begin: Alignment.topCenter,
            end: Alignment.bottomCenter,
          ),
        ),
        child: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                AppLocalizations.of(context)?.translate('start_conversion') ?? 'Start Conversion',
                style: TextStyle(fontSize: 24, fontWeight: FontWeight.bold, color: Colors.blueAccent),
              ),
              SizedBox(height: 30),
              ElevatedButton(
                onPressed: () {
                  Navigator.push(
                    context,
                    MaterialPageRoute(builder: (context) => ConverterScreen()),
                  );
                },
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(horizontal: 40, vertical: 15),
                  backgroundColor: Colors.blueAccent,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(20),
                  ),
                ),
                child: Text(
                  AppLocalizations.of(context)?.translate('start_conversion') ?? 'Start Conversion',
                  style: TextStyle(fontSize: 18, color: Colors.white),
                ),
              ),
              SizedBox(height: 40),
              Text(
                AppLocalizations.of(context)?.translate('select language') ?? 'Select Language:',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.w600, color: Colors.blueAccent),
              ),
              SizedBox(height: 20),
              Wrap(
                spacing: 20,
                children: [
                  _buildLanguageButton(context, 'English', Locale('en', 'US')),
                  
                  _buildLanguageButton(context, 'Français', Locale('fr', 'FR')),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }

  Widget _buildLanguageButton(BuildContext context, String label, Locale locale) {
    return ElevatedButton(
      onPressed: () {
        onLanguageChange(locale);
      },
      style: ElevatedButton.styleFrom(
        padding: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        backgroundColor: Colors.white,
        side: BorderSide(color: Colors.blueAccent, width: 2),
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(20),
        ),
      ),
      child: Text(
        label,
        style: TextStyle(fontSize: 16, color: Colors.blueAccent),
      ),
    );
  }
}