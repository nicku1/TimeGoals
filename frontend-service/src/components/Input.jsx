export const Input = ({ label, onChange, name, value }) => {
  return (
    <div class="my-1">
      <label>{label}</label>
      <input
        class="ml-2 px-1 border rounded-lg"
        onChange={onChange}
        name={name}
        value={value}
      />
    </div>
  );
};
