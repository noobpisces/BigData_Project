import couchdb
import json
import pandas as pd
import sys

pd.set_option('display.max_columns', None)

def login():
    while True:
        username = input("Nhập username: ")
        password = input("Nhập password: ")
        
        str_conn = f"http://{username}:{password}@localhost:5984"
        couch = couchdb.Server(str_conn)

        try:
            # Thử kết nối để xác thực đăng nhập
            couch.version()
        except couchdb.Unauthorized:
            print("Đăng nhập không thành công. Vui lòng thử lại.")
        finally:
            print("Đăng nhập thành công.")
            return couch

def display_first_10_documents(db):
    all_docs = []
    count = 0
    for doc_id in db:
        if count < 10:
            doc = db.get(doc_id)
            count += 1
            all_docs.append(doc)
        else:
            break
    
    df = pd.DataFrame(all_docs)
    # df = df.drop(df.columns[1], axis=1)
    print(df)

def display_all_documents(db):
    all_docs = []
    for doc_id in db:
        doc = db.get(doc_id)
        all_docs.append(doc)

    df = pd.DataFrame(all_docs)
    # df = df.drop(df.columns[1], axis=1)
    print(df)

def import_from_json_file(db, file_path):
    if file_path:
        with open(file_path) as jsonfile:
            for row in jsonfile:
                db_entry = json.loads(row)
                db.save(db_entry)
        print("Import thành công từ file JSON.")
    else:
        print("Không chọn file.")

def export_to_json_file(db, db_name):
    all_docs = []
    for doc_id in db:
        doc = db.get(doc_id)
        all_docs.append(doc)

    df = pd.DataFrame(all_docs)
    df = df.drop(df.columns[1], axis=1)
    df.to_json(f'{db_name}.json', orient='records', lines=True)
    print("Export thành công ra file JSON.")

def main():
    print("\nNHẬP XUẤT XEM DỮ LIỆU CỦA DATABASE TRONG COUCHDB\n")
    couch = login()
    db_name = input("\nNhập tên database: ")
    db = couch[db_name] if db_name in couch else couch.create(db_name)
    while True:
        print("\n---- MENU ----")
        print("1. Xem 10 documents đầu tiên")
        print("2. Xem toàn bộ documents")
        print("3. Import từ file JSON")
        print("4. Export ra file JSON")
        print("5. Thoát")
        choice = input("Chọn chức năng (1-5): ")

        if choice == "1":
            display_first_10_documents(db)
        elif choice == "2":
            display_all_documents(db)
        elif choice == "3":
            file_path = input("Nhập đường dẫn file: ")
            import_from_json_file(db, file_path)
        elif choice == "4":
            export_to_json_file(db, db_name)
        elif choice == "5":
            db = None
            couch = None
            print("Tạm biệt.")
            sys.exit()
        else:
            print("Chức năng không hợp lệ. Vui lòng chọn lại.")

if __name__ == '__main__':
    main()
