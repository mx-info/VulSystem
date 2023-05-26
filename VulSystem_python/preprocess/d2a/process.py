import pandas as pd
from normalization import normalization, normalization2

train = pd.read_csv('d2a_lbv1_function_train.csv')
test = pd.read_csv('d2a_lbv1_function_dev.csv')

train['code'] = normalization(train)
test['code'] = normalization(test)

train_d2a = pd.DataFrame(train, columns=['code', 'label'])
test_d2a = pd.DataFrame(test, columns=['code', 'label'])

# train_d2a.to_csv('./corpus.csv', encoding='utf-8')

# train_d2a.to_csv('./train.csv', encoding='utf-8')
# test_d2a.to_csv('./test.csv', encoding='utf-8')

print(train)
