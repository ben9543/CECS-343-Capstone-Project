#include <iostream>
#include <iomanip>
#include <string>
#include <map>
#include <locale>
#include <vector>

// Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec

using namespace std;

class Tenant {
public:
    Tenant(string name, string aptNum){
        this->name = name;
        this->aptNum = aptNum;
    }
    // Getters
    string getName(){return this->name;}
    string getAptNum(){return this->aptNum;}
private:
    string name;
    string aptNum; // need to check is numeric or not
};

class Expense{
public:
    Expense(int month,int day,string category,string payee,double amount){
        this->month = month;
        this->day = day;
        this->category = category;
        this->payee = payee;
        this->amount = amount;
    }
    // Getters
    int getMonth(){return this->month;}
    int getDay(){return this->day;}
    string getCategory(){return this->category;}
    string getPayee(){return this->payee;}
    double getAmount(){return this->amount;}
private:
    int month;
    int day;
    string category;
    string payee;
    double amount;
};


static map<string, Tenant*> tenantsName;
static map<string, bool> aptOccupancy;
static vector<Tenant> tenants;
static map<string, vector<double>> rents;
static vector<Expense> expenses;


bool isNumeric(string s){ // Does not allow whitespace
    for (auto c : s) {
        if (!isdigit(c)) return false;
    }
    return true;
}

bool isDouble(string s){
    int count = 0;
    for (auto c : s) {
        if (!isdigit(c)) {
            if (c=='.'){
                if (count <= 0) count++;
                else return false;
            }else return false;
        }
    }
    return true;
}

bool validateMonth(string month){
    if(!isNumeric(month))return false;
    return true;
}

bool validateDay(string day){
    if(!isNumeric(day))return false;
    return true;
}

void getUserInput(string& s, string message){
    do {
        cout << message << endl;
        getline(cin, s);
    } while (cin.fail());
}

bool recordTenant(){
    string tenantName;
    string aptNum;
    
    /* Input starts */
    
    // 1. Tenant's name
    getUserInput(tenantName, "Enter tenant's name: ");
    if (tenantsName.count(tenantName)){ // Check if tenant with the same name exists
        cout << "The tenant with the same name already exists. Try to put identifier to the name." << endl;
        return false;
    }

    // 2. Apt Number
    getUserInput(aptNum, "Enter tenant's apartment number: ");
    if(!isNumeric(aptNum)){// Check aptNum is numeric or not
        cout << "Wrong apartment number" << endl;
        return false;
    }
    if(aptOccupancy.count(aptNum)){// Check aptNum is occupied
        cout << "The apartment is occupied" << endl;
        return false;
    }
    
    // Passed all validation and add the tenant's information
    Tenant t(tenantName, aptNum);
    tenants.push_back(t);
    tenantsName[tenantName] = &t;
    aptOccupancy[aptNum] = true;
    
    return true;
}

bool recordRent(){
    string tenantName;
    string amountPaid;
    string rentMonth;
    string tenantAptNum;
    double amountPaid_d;
    int rentMonth_i;
    
    /* Input starts */
    
    // 1. Tenant's name
    getUserInput(tenantName, "Enter tenant's name: ");
    if(!tenantsName.count(tenantName)){ // Check if the tenant with the name exists
        cout << "No tenant with that name." << endl;
        return false;
    }
    tenantAptNum = tenantsName[tenantName]->getAptNum();
    
    // 2. Amount paid
    getUserInput(amountPaid, "Enter amount paid (insert numeric value without whitespace): ");
    
    if(!isDouble(amountPaid)){// Check if the character is double
        cout << "Invalid amount paid." << endl;
        return false;
    }
    amountPaid_d = stod(amountPaid);
    
    // 3. Month of the rent
    getUserInput(rentMonth, "Enter month of the rent (insert numeric value without whitespace): ");
    if(!isNumeric(rentMonth)){
        return false;
    }
    rentMonth_i = stoi(rentMonth);
    if(rentMonth_i < 1 || rentMonth_i > 12){
        cout << "Wrong input. The input should be an integer between 1 - 12" << endl;
        return false;
    }
    
    // Passed all validation and add rent data to rents
    rents[tenantAptNum] = vector<double>(12);
    rents[tenantAptNum][rentMonth_i-1] = amountPaid_d;
    
    return true;
}

bool recordExpense(){
    string month, day, category, payee, amount;
    int month_i, day_i;
    double amount_d;
    
    // 1. Month
    getUserInput(month, "Enter month of the expense (insert numeric value without whitespace): ");
    if(!isNumeric(month)){
        cout << "Invalid month value." << endl;
        return false;
    }
    month_i = stoi(month);
    if(month_i < 1 || month_i > 12){
        cout << "Wrong input. The input should be an integer between 1 - 12" << endl;
        return false;
    }
    
    // 2. Day
    getUserInput(day, "Enter day of the expense (insert numeric value without whitespace): ");
    if(!isNumeric(month)){
        cout << "Invalid month value." << endl;
        return false;
    }
    day_i = stoi(day);
    if(day_i < 1 || day_i > 31){
        cout << "Wrong input. The input should be an integer between 1 - 31" << endl;
        return false;
    }
    
    // 3. Category
    getUserInput(category, "Enter category of the expense: ");
    // May have more restrictions in the future
    
    // 4. Payee
    getUserInput(payee, "Enter payee of the expense : ");
    
    // 5. Amount
    getUserInput(amount, "Enter amount of the expense (insert numeric value without whitespace): ");
    if(!isDouble(amount)){
        cout << "Invalid amount value." << endl;
        return false;
    }
    amount_d = stod(amount);
    expenses.push_back(Expense(month_i, day_i, category, payee, amount_d));
    return true;
}

void displayMenu(){
    cout << "\n==========================" << endl;
    cout << "Enter\n'i' to input data,\n'd' to display a report,\n'q' to quit program:" << endl;
    cout << "==========================\n" << endl;
}

void displayTenants(){
    cout << left << setw(7);
    cout << "Apt#" << "Tenant name" << endl;
    cout << "--------------------" << endl;
    for (auto v: tenants){
        cout << setw(7);
        cout << v.getAptNum() << v.getName() << endl;
    }
}

void displayExpenses(){
    cout << left << setw(10);
    cout << "Date";
    cout << setw(10);
    cout << "Payee";
    cout << setw(12);
    cout << "Amount";
    cout << "Category" << endl;
    cout << "---------------------------------------" << endl;
    for (auto e: expenses){
        cout << setw(10);
        cout << to_string(e.getMonth()) + "/" + to_string(e.getDay());
        cout << setw(10);
        cout << e.getPayee();
        cout << "$";
        cout << setw(12);
        cout << fixed << setprecision(2) << e.getAmount();
        cout << e.getCategory() << endl;
    }
}

void displayRents(){
    // Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec
}
void displayAnnualReport(){
    
}
bool isValidInput(char input){
    if (input == 'i' || input == 'd' || input == 'q')
        return true;
    else
        return false;
}
bool isValidDisplayInput(char input){
    if (input == 't' || input == 'r' || input == 'e' || input=='a')
        return true;
    else
        return false;
}

int main()
{
    char userInput;
    bool isRunning = true;
    
    while(isRunning){
        
        // 1. Menu & ask user input
        do{
            displayMenu();
            cin >> userInput;
            
        }while(!cin.fail() && !isValidInput(userInput));
        
        // 2. Switch depends on user input
        switch (userInput) {
            case 'i':
                break;
            case 'q':
                isRunning = false;
                break;
            default:
                cout << "Invalid input. Try it again" << endl;
                break;
        }
    }
    cout << "Bye!" << endl;
    
    /*
    for (auto m: tenantsName){
        cout << m.first << ":" << m.second << endl;
    }
    for (auto m: rents){
        cout << m.first << ":";
        for (auto v: m.second) cout << v << " ";
        cout << endl;
    }*/
    
    return 0;
}


