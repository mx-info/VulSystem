import pandas as pd
from normalization import normalization, normalization2
from preprocess.preorder.preorder import parse_ast, trans_to_sequences

train = pd.read_csv('d2a_lbv1_function_train.csv')
test = pd.read_csv('d2a_lbv1_function_dev.csv')

train['code'] = train['code'].apply(parse_ast)
test['code'] = test['code'].apply(parse_ast)

corpus = train['code'].apply(trans_to_sequences)

with open('./corpus2.txt', 'w', encoding='utf-8') as f:
    for path in corpus:
        f.write(path)
        f.write('\n')


# train['code'] = train['code'].apply(trans_to_sequences)
# test['code'] = test['code'].apply(trans_to_sequences)
#
# train_d2a = pd.DataFrame(train, columns=['code', 'label'])
# test_d2a = pd.DataFrame(test, columns=['code', 'label'])
#
# # train_d2a.to_csv('./corpus.csv', encoding='utf-8')
#
# train_d2a.to_csv('./train.csv', encoding='utf-8')
# test_d2a.to_csv('./test.csv', encoding='utf-8')

# print(train)
