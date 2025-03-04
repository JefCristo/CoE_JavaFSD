import { Link } from "react-router-dom";
import { useContext } from "react";
import CartContext from "../context/CartContext";

const Navbar = () => {
  const { cart } = useContext(CartContext); // Get cart state for count

  return (
    <nav>
      <div className="container">
        <div className="flex justify-between items-center w-full">
          <div className="flex-1"></div> {/* Empty space on left */}
          <Link to="/" className="text-white text-2xl font-bold">
            AtoZ
          </Link>
          <Link to="/cart" className="cart-button">
            Cart ({cart.length})
          </Link>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;