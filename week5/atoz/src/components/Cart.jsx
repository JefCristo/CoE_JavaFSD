import { useContext } from "react";
import CartContext from "../context/CartContext";

const Cart = () => {
  const { cart, removeFromCart } = useContext(CartContext);
  const totalPrice = cart.reduce((total, item) => total + item.price, 0);

  return (
    <div className="cart">
      <h2>Your Cart</h2>
      {cart.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <div>
          {cart.map((item) => (
            <div key={item.id} className="cart-item">
              <span>
                {item.name} - ${item.price}
              </span>
              <button onClick={() => removeFromCart(item)}>Remove</button>
            </div>
          ))}
          <div className="cart-total">Total: ${totalPrice}</div>
        </div>
      )}
    </div>
  );
};

export default Cart;