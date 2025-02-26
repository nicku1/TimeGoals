export const useAuth = (node, accountCredentials) => {
  fetch(`http://localhost:8084/${node}`, {
    method: "POST",
    mode: "cors",
    headers: {
      "Access-Control-Allow-Origin": "*",
    },
    body: JSON.stringify(accountCredentials),
  }).then((res) => console.log(res));
};
