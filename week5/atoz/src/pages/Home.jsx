import ProductCard from "../components/ProductCard";

const products = [
  { id: 1, name: "boat wireless rockerz", price: 15, image: "/images/product1.jpg" },
  { id: 2, name: "boat wireless revulz", price: 13, image: "/images/product2.jpg" },
  { id: 3, name: "boat wired deep base", price: 8, image: "/images/product3.jpg" },
];

const Home = () => {
  return (
    <div className="container">
      <h1>Products</h1>
      <div className="product-list">
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </div>
    </div>
  );
};

export default Home;