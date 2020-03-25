import { makeQuantizer } from "./Quantizer";

const quantize = makeQuantizer(
  { threshold: 100, value: 'A' },
  { threshold: 90, value: 'B' },
  { threshold: 80, value: 'C' },
  { threshold: 70, value: 'D' },
  { threshold: 60, value: 'F' },
);

const scores = (function next(n: number): number[] {
  return n <= 100 ? [n, n+1].concat(next(n+10)) : [];
})(0);

for (const score of scores) {
  const grade = quantize(score, 'N/A');
  console.log(`Score: ${score}, Grade: ${grade}`);
}

