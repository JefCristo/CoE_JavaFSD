import 'package:sqflite/sqflite.dart';
import 'package:path/path.dart';

class DatabaseService {
  static final DatabaseService _instance = DatabaseService._internal();
  factory DatabaseService() => _instance;

  DatabaseService._internal();

  Database? _database;

  Future<Database> get database async {
    if (_database != null) return _database!;
    _database = await _initDatabase();
    return _database!;
  }

  Future<Database> _initDatabase() async {
    final dbPath = await getDatabasesPath();
    final path = join(dbPath, 'currency_converter.db');

    return await openDatabase(
      path,
      version: 1,
      onCreate: (db, version) async {
        await db.execute('''
          CREATE TABLE conversion_history (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            from_currency TEXT,
            to_currency TEXT,
            amount REAL,
            result REAL,
            timestamp TEXT
          )
        ''');
      },
    );
  }

  Future<void> saveConversion(
    String fromCurrency,
    String toCurrency,
    double amount,
    double result,
  ) async {
    final db = await database;
    await db.insert('conversion_history', {
      'from_currency': fromCurrency,
      'to_currency': toCurrency,
      'amount': amount,
      'result': result,
      'timestamp': DateTime.now().toIso8601String(),
    }, conflictAlgorithm: ConflictAlgorithm.replace);
  }

  Future<List<Map<String, dynamic>>> getConversionHistory() async {
    final db = await database;
    return await db.query('conversion_history', orderBy: 'timestamp DESC');
  }

  Future<void> clearHistory() async {
    final db = await database;
    await db.delete('conversion_history');
  }
}
