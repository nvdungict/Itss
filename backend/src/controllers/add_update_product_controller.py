from src.entities.product import Product
import datetime
import psycopg2  

class ProductController:
    daily_deletion_count = 0
    daily_deletion_date = datetime.date.today()

    def __init__(self, product=None):
        if product is None:
            self.product = Product("", 0, 0, "", [])
        else:
            self.product = product

    def add_product(self, name, price, stock, description, images=None):
        self.product = Product(name, price, stock, description, images)

    def update_product(self, name=None, price=None, stock=None, description=None, images=None):
        self.product.update_product(name, price, stock, description, images)

    def delete_product(self, cascade_products=None):
        if cascade_products is None:
            cascade_products = []
        if len(cascade_products) > 10:
            raise Exception("Bạn chỉ được xóa tối đa 10 sản phẩm cùng lúc.")

        today = datetime.date.today()
        if today != ProductController.daily_deletion_date:
            ProductController.daily_deletion_date = today
            ProductController.daily_deletion_count = 0

        total_deletions = 1 + len(cascade_products)  
        if ProductController.daily_deletion_count + total_deletions > 30:
            raise Exception("Vì lý do bảo mật, bạn chỉ được xóa tối đa 30 sản phẩm trong một ngày.")

        self._delete(self.product)

        for prod in cascade_products:
            self._delete(prod)

        ProductController.daily_deletion_count += total_deletions

    def _delete(self, product):
        conn = None
        cursor = None
        try:
            conn = psycopg2.connect(
                database="your_database_name",
                user="your_username",
                password="your_password",
                host="localhost",
                port="5432"
            )
            cursor = conn.cursor()
            delete_query = "DELETE FROM products WHERE id = %s"
            cursor.execute(delete_query, (product.id,))
            conn.commit()
            print(f"Đã xóa sản phẩm: {product}")

            # Log the deletion operation into the operation history database
            self._log_operation("delete", product)
        except Exception as e:
            if conn:
                conn.rollback()
            print(f"Lỗi khi xóa sản phẩm {product}: {e}")
        finally:
            if cursor:
                cursor.close()
            if conn:
                conn.close()

    def _log_operation(self, operation, product):
        conn = None
        cursor = None
        try:
            conn = psycopg2.connect(
                database="your_operation_history_database",
                user="your_username",
                password="your_password",
                host="localhost",
                port="5432"
            )
            cursor = conn.cursor()
            log_query = ("INSERT INTO operation_history (product_id, operation, operation_time) "
                         "VALUES (%s, %s, %s)")
            cursor.execute(log_query, (product.id, operation, datetime.datetime.now()))
            conn.commit()
            print(f"Đã ghi log {operation} cho sản phẩm: {product}")
        except Exception as e:
            if conn:
                conn.rollback()
            print(f"Lỗi khi ghi log thao tác {operation} cho sản phẩm {product}: {e}")
        finally:
            if cursor:
                cursor.close()
            if conn:
                conn.close()

