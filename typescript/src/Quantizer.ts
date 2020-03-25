import { flow, sortBy, find } from 'lodash/fp';

type Threshold<T, V> = {
    threshold: T;
    value: V;
}

export class Quantizer<T, V> {
  private thresholds: Threshold<T, V>[];

  constructor(...thresholds: Threshold<T, V>[]) {
    this.thresholds = thresholds;
  }

  addThreshold(threshold: T, value: V) {
    this.thresholds.push({
      threshold,
      value
    })
  }

  quantize(from: T, defaultValue?: V): V | undefined {
    const fn = flow(
      sortBy('threshold'),
      find<Threshold<T, V>>(th => from <= th.threshold )
    );

    return fn(this.thresholds)?.value ?? defaultValue;
  }
}

export function makeQuantizer<T, V>(...thresholds: Threshold<T, V>[]) {
  const quantizer = new Quantizer<T,V>(...thresholds);
  return (from: T, defaultValue?: V): V | undefined => quantizer.quantize(from, defaultValue);
}
