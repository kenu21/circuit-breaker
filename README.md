# circuit-breaker

```bash
for i in {1..100}; do
  printf "%s " "$(curl -s localhost:8080/demo)"
done
